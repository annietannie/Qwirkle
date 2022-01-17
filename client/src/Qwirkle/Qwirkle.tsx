import React, { useState } from "react";
import { StartGame } from "./StartGame";
import { Play } from "./Play";
import type { GameState } from "../gameState";

export function Qwirkle() {

    const [ gameState, setGameState ] = useState<GameState | undefined>(undefined);

    if (localStorage.getItem("gameState")) {
        const gameStateFromLS = JSON.parse(localStorage.getItem("gameState"));
        return <Play gameState={gameStateFromLS} setGameState={setGameState} />
    }
    
    if (!gameState) {
        return <StartGame setGameState={setGameState} />
    }

    return <Play gameState={gameState} setGameState={setGameState} />
}