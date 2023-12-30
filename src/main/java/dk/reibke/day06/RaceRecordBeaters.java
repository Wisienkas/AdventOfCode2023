package dk.reibke.day06;

public record RaceRecordBeaters(Race race, long pressTime, long distance) {
    public static RaceRecordBeaters from(Race race, long pressTime) {
        long timeInMotion = race.raceTime() - pressTime;
        long distance = timeInMotion * pressTime;
        return new RaceRecordBeaters(race, pressTime, distance);
    }
}
