export interface GameState {
    players: Player[];
    gameBoard: Board;
    numberOfTilesLeft: number;
    isGameOver: boolean;
}

interface Player {
    tiles: Tile[];
    hasTurn: boolean;
    score: number;
}

interface Board {
    tiles: Tile[][];
}

interface Tile {
    shape: "CIRCLE" | "SQUARE" | "DIAMOND" | "CLUB" | "FOURSTAR" | "EIGHTSTAR";
    colour: "RED" | "ORANGE" | "YELLOW" | "GREEN" | "BLUE" | "PURPLE";
    thisTurn: boolean;
}