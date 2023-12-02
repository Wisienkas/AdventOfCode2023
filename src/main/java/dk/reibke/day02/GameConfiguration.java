package dk.reibke.day02;

public record GameConfiguration(CubeSet cubeSet) {

    public boolean isValidGame(Game game) {
        return game.getCubeSets().stream()
                .allMatch(this::isPossibleCubeSet);
    }

    private boolean isPossibleCubeSet(CubeSet cubeSet) {
        if (cubeSet.blue() > this.cubeSet.blue()) {
            return false;
        }
        if (cubeSet.red() > this.cubeSet.red()) {
            return false;
        }
        return cubeSet.green() <= this.cubeSet.green();
    }
}
