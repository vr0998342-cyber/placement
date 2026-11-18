
import java.util.*;

class Song {
    String title, artist, genre;
    int duration;
    int plays = 0;

    Song(String title, String artist, String genre, int duration) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Song> songs = new ArrayList<>();
    static ArrayList<Song> playlist = new ArrayList<>();

    static int current = 0;

    // Add song to library
    static void addSong() {
        System.out.print("Enter title: ");
        String title = sc.nextLine();

        System.out.print("Enter artist: ");
        String artist = sc.nextLine();

        System.out.print("Enter genre: ");
        String genre = sc.nextLine();

        System.out.print("Enter duration in seconds: ");
        int duration = sc.nextInt();
        sc.nextLine();

        songs.add(new Song(title, artist, genre, duration));

        System.out.println("Song added!");
    }

    // Show all songs
    static void showSongs() {
        for (int i = 0; i < songs.size(); i++) {
            System.out.println(i + ". " + songs.get(i).title);
        }
    }

    // Add song to playlist
    static void addToPlaylist() {
        showSongs();

        System.out.print("Enter song number: ");
        int n = sc.nextInt();
        sc.nextLine();

        if (n >= 0 && n < songs.size()) {
            playlist.add(songs.get(n));
            System.out.println("Added to playlist!");
        } else {
            System.out.println("Invalid song number");
        }
    }

    // Remove song
    static void removeSong() {
        showPlaylist();

        System.out.print("Enter position to remove: ");
        int n = sc.nextInt();
        sc.nextLine();

        if (n >= 0 && n < playlist.size()) {
            playlist.remove(n);
            current = 0;
            System.out.println("Song removed!");
        } else {
            System.out.println("Invalid position");
        }
    }

    // Show playlist
    static void showPlaylist() {
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println(i + ". " + playlist.get(i).title);
        }
    }

    // Play current song
    static void play() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty!");
            return;
        }

        Song s = playlist.get(current);
        System.out.println("Now playing: " + s.title);
        s.plays++;
    }

    // Next song
    static void next() {
        if (!playlist.isEmpty()) {
            current = (current + 1) % playlist.size();
            play();
        }
    }

    // Previous song
    static void previous() {
        if (!playlist.isEmpty()) {
            current = (current - 1 + playlist.size()) % playlist.size();
            play();
        }
    }

    // Shuffle playlist
    static void shuffle() {
        Collections.shuffle(playlist);
        current = 0;
        System.out.println("Playlist shuffled!");
    }

    // Total duration
    static void totalDuration() {
        int total = 0;

        for (Song s : playlist) {
            total += s.duration;
        }

        System.out.println("Total duration: " + total + " seconds");
    }

    // Most played songs
    static void mostPlayed() {
        songs.sort((a, b) -> b.plays - a.plays);

        System.out.println("Most played songs:");

        for (Song s : songs) {
            if (s.plays > 0) {
                System.out.println(s.title + " - " + s.plays + " plays");
            }
        }
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== BEATQUEUE =====");
            System.out.println("1. Add Song");
            System.out.println("2. Show Songs");
            System.out.println("3. Add to Playlist");
            System.out.println("4. Remove from Playlist");
            System.out.println("5. Show Playlist");
            System.out.println("6. Play Song");
            System.out.println("7. Next Song");
            System.out.println("8. Previous Song");
            System.out.println("9. Shuffle");
            System.out.println("10. Total Duration");
            System.out.println("11. Most Played Songs");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: addSong(); break;
                case 2: showSongs(); break;
                case 3: addToPlaylist(); break;
                case 4: removeSong(); break;
                case 5: showPlaylist(); break;
                case 6: play(); break;
                case 7: next(); break;
                case 8: previous(); break;
                case 9: shuffle(); break;
                case 10: totalDuration(); break;
                case 11: mostPlayed(); break;
                case 0:
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
