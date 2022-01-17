import React, { useState } from "react";
import type { GameState } from "../gameState";
import "./StartGame.css";

type StartGameProps = {
    setGameState(newGameState: GameState): void;
}

export function StartGame({ setGameState }: StartGameProps) {

    const [errorMessage, setErrorMessage] = useState("");
    const [numberOfPlayers, setNumberOfPlayers] = useState("");

    async function tryStartGame(e: React.FormEvent) {

        e.preventDefault(); // Prevent default browser behavior of submitting forms
        if (!numberOfPlayers) {
            setErrorMessage("You need at least one player to play Qwirkle");
            return;
        }
        setErrorMessage("");

        try {
                const response = await fetch('qwirkle/api/start', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(1)
            });
            
            if (response.ok) {
                const gameState = await response.json();
                setGameState(gameState);
                console.log(gameState);
            } else {
                
                console.error(response.statusText);
                console.log("I did a thing");
            }
            
        } catch (error) {
            console.error(error);
        }
    }

    return (
        <form onSubmit={(e) => tryStartGame(e)}>
            <input value={numberOfPlayers}
                placeholder="Number of Players"
                onChange={(e) => setNumberOfPlayers(e.target.value)}
            />

            <p className="errorMessage">{errorMessage}</p>

            <button className="startGameButton" type="submit">
                Play Qwirkle!
            </button>
        </form>
    )
}