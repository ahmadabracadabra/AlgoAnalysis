package pp14;

import java.util.Arrays;

public class RRTournament {

    public static void generateSchedule(int numTeams) {
        int n = numTeams;
        boolean hasBye = false;

        if (n % 2 != 0) {
            n++; 
            hasBye = true;
        }

        int[][] schedule = new int[n - 1][n / 2 * 2]; 
        int[][] teams = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(teams[i], 0);
        }

        backtrackSchedule(schedule, teams, 0, 0, n);

        printSchedule(schedule, numTeams, hasBye);
    }

    private static boolean backtrackSchedule(int[][] schedule, int[][] teams, int day, int game, int n) {
        if (day == n - 1) {
            return true;
        }

        if (game == n / 2) {
            return backtrackSchedule(schedule, teams, day + 1, 0, n);
        }

        for (int team1 = 0; team1 < n; team1++) {
            for (int team2 = team1 + 1; team2 < n; team2++) {
                if (validMatch(teams, team1, team2, day)) {
                    schedule[day][game * 2] = team1;  
                    schedule[day][game * 2 + 1] = team2; 
                    teams[team1][team2] = day + 1;
                    teams[team2][team1] = day + 1;

                    if (backtrackSchedule(schedule, teams, day, game + 1, n)) {
                        return true;
                    }

                    schedule[day][game * 2] = 0; 
                    schedule[day][game * 2 + 1] = 0;
                    teams[team1][team2] = 0;
                    teams[team2][team1] = 0;
                }
            }
        }
        return false;
    }

    private static boolean validMatch(int[][] teams, int team1, int team2, int day) {
        if (teams[team1][team2] != 0) {
            return false;
        }

        for (int i = 0; i < teams.length; i++) {
            if (teams[team1][i] == day + 1 && i != team2) {
                return false;
            }
            if (teams[team2][i] == day + 1 && i != team1) {
                return false;
            }
        }

        return true;
    }

    private static void printSchedule(int[][] schedule, int numTeams, boolean hasBye) {
        int n = numTeams;
        if (hasBye) {
            n++;
        }
        for (int day = 0; day < n - 1; day++) {
            System.out.println("Day " + (day + 1) + ":");
            for (int game = 0; game < n / 2; game++) {
                int team1 = schedule[day][game * 2];
                int team2 = schedule[day][game * 2 + 1];

                if (hasBye && (team1 == n - 1 || team2 == n - 1)) {
                    int actualTeam;
                    if (team1 == n - 1) {
                        actualTeam = team2;
                    } else {
                        actualTeam = team1;
                    }
                    System.out.println("  Team " + (actualTeam + 1) + " vs Bye");
                } else {
                    System.out.println("  Team " + (team1 + 1) + " vs Team " + (team2 + 1));
                }
            }
        }
    }
} // end class
