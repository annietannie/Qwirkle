export interface GameState {
    players: Player[];
    gameBoard: Board;
    numberOfTilesLeft: number;
}

interface Player {
    tiles: Tile[];
    hasTurn: boolean;
}

interface Board {
    tiles: Tile[][];
}

interface Tile {
    shape: "CIRCLE" | "SQUARE" | "DIAMOND" | "CLUB" | "FOURSTAR" | "EIGHTSTAR";
    colour: "RED" | "ORANGE" | "YELLOW" | "GREEN" | "BLUE" | "PURPLE";
}