package BOJ;

import java.io.*;
import java.util.*;

public class BOJ20541 {

    private static Album now;
    private static Album album;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 명령어 개수
        album = new Album(null, "album");
        now = album;

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            String S = st.nextToken();

            if (cmd.equals("mkalb")) {
                mkalb(S);
            } else if (cmd.equals("rmalb")) {
                int[] val = rmalb(S);
                System.out.println(val[0] + " " + val[1]);
            } else if (cmd.equals("insert")) {
                insert(S);
            } else if (cmd.equals("delete")) {
                System.out.println(delete(S));
            } else if (cmd.equals("ca")) {
                ca(S);
                System.out.println(now.name);
            }
        }
    }

    // 앨범을 생성하는 함수
    private static void mkalb(String albName) {
        // - 현재 앨범에 S의 이름을 가진 앨범을 생성합니다.
        // - 현재 앨범에 속해있는 앨범 중 동일한 이름을 가진 앨범이 존재하면 앨범을 생성하지 않습니다.
        if (!now.albums.containsKey(albName)) {
            now.albums.put(albName, new Album(now, albName));
        } else {
            System.out.println("duplicated album name");
        }
    }

    // 앨범과 사진을 삭제하는 함수
    private static int[] rmalb(String cmd) {
        if (cmd.equals("-1") && !now.albums.isEmpty()) {
            // 현재 앨범에 속해있는 앨범이 존재한다면, 이름이 사전순으로 가장 빠른 앨범을 삭제
            int[] ans = countRemovedFilesAndAlbums(now.albums.firstEntry().getValue());
            now.albums.pollFirstEntry();
            ans[0]++;
            return ans;
        } else if (cmd.equals("0")) {
            // 현재 앨범에 속해있는 모든 앨범을 삭제 합니다.
            int[] ans = new int[2];
            for (String key : now.albums.keySet()) {
                int[] tmp = countRemovedFilesAndAlbums(now.albums.get(key));
                ans[0] += tmp[0] + 1;
                ans[1] += tmp[1];
            }
            now.albums.clear();
            return ans;
        } else if (cmd.equals("1") && !now.albums.isEmpty()) {
            // 현재 앨범에 속해있는 앨범이 존재한다면, 이름이 사전순으로 가장 느린 앨범을 삭제 합니다.
            int[] ans = countRemovedFilesAndAlbums(now.albums.lastEntry().getValue());
            ans[0]++;
            now.albums.pollLastEntry();
            return ans;
        } else {
            // 현재 앨범에 속해있는 앨범 중 S 의 이름을 가진 앨범이 존재한다면, 해당 앨범을 삭제합니다.
            if (now.albums.containsKey(cmd)) {
                int[] ans = countRemovedFilesAndAlbums(now.albums.get(cmd));
                ans[0]++;
                now.albums.remove(cmd);
                return ans;
            }
        }

        // 삭제된게 없다면 {0, 0} 반환
        return new int[2];
    }

    private static int[] countRemovedFilesAndAlbums(Album removedAlb) {
        int[] ans = new int[2];
        if (!removedAlb.albums.isEmpty()) {
            for (String key : removedAlb.albums.keySet()) {
                ans[0]++;
                int[] tmp = countRemovedFilesAndAlbums(removedAlb.albums.get(key));
                ans[0] += tmp[0];
                ans[1] += tmp[1];
            }
            removedAlb.albums.clear();
        }

        int size = removedAlb.photos.size();
        removedAlb.photos.clear();
        ans[1] += size;

        return ans;
    }

    // 사진을 생성하는 함수
    private static void insert(String photoName) {
        if (!now.photos.contains(photoName)) {
            now.photos.add(photoName);
        } else {
            System.out.println("duplicated photo name");
        }
    }

    // 사진을 삭제하는 함수
    private static int delete(String cmd) {
        if (cmd.equals("-1") && !now.photos.isEmpty()) {
            // 현재 앨범에 속해있는 사진 중 S 의 이름을 가진 사진이 존재한다면, 해당 사진을 삭제합니다.
            now.photos.pollFirst();
            return 1;
        } else if (cmd.equals("0")) {
            // 현재 앨범에 속해있는 모든 사진을 삭제 합니다.
            int size = now.photos.size();
            now.photos.clear();
            return size;
        } else if (cmd.equals("1") && !now.photos.isEmpty()) {
            // 현재 앨범에 속해있는 사진이 존재한다면, 이름이 사전순으로 가장 느린 사진을 삭제 합니다.
            now.photos.pollLast();
            return 1;
        } else {
            // 현재 앨범에 속해있는 앨범 중 S 의 이름을 가진 사진이 존재한다면, 해당 사진을 삭제합니다.
            if (now.photos.contains(cmd)) {
                now.photos.remove(cmd);
                return 1;
            }
        }

        return 0;
    }

    private static void ca(String cmd) {
        if (cmd.equals("..") && now.parent != null) {
            now = now.parent;
        } else if (cmd.equals("/")) {
            now = album;
        } else {
            if (now.albums.containsKey(cmd)) {
                now = now.albums.get(cmd);
            }
        }
    }

    private static class Album {
        Album parent;
        TreeMap<String, Album> albums;
        TreeSet<String> photos;
        String name;

        Album(Album parent, String name) {
            this.parent = parent;
            this.name = name;
            this.albums = new TreeMap<>();
            this.photos = new TreeSet<>();
        }
    }
}
