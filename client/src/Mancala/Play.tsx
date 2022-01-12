import React from "react";
import type { GameState } from "../gameState";
import "./Play.css";

type PlayProps = {
    gameState: GameState;
    setGameState(newGameState: GameState): void;
}

export function Play({ gameState, setGameState }: PlayProps) {
    const pitP1 = gameState.players[0].pits.slice(0,6);
    const pitP2Temp = gameState.players[1].pits.slice(0,6);
    const pitP2 = pitP2Temp.reverse();

    async function playBowl(index: number) {     
        try {
            const response = await fetch('mancala/api/play', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ index: index })
            });

            if (response.ok) {
                const gameState = await response.json();
                console.log(gameState);
                    if ("players" in gameState) {
                        localStorage.setItem("gameState", JSON.stringify(gameState));
                        setGameState(gameState);
                    } else {
                        alert(gameState.message);
                    }
            } else {
                console.error(response.statusText);
            }
        } catch (error) {
            console.error(error);
        }
    }

    async function rageQuit() {
        localStorage.clear();
        setGameState(undefined);
        window.location.reload(false);
    }

    return (
        <div>
            
            <div className="gameboard">
            <h1 className="title">{gameState.players[0].name} vs {gameState.players[1].name}</h1>
                <p> {(gameState.players[1].hasTurn) ? `>>> ${gameState.players[1].name} <<<` : gameState.players[1].name}</p>

                {/* Bowls Player 2 */}
                <div className="bowls">
                    {pitP2.map(pit => <button 
                        type="button" 
                        className="bowlP2" 
                        key={pit.index}
                        onClick={()=>playBowl(pit.index)}
                        > 
                            {pit.nrOfStones !=0 ? pit.nrOfStones : ""} 
                        </button>
                    )}
                </div>

                {/* Kalahas */}
                <div>
                    <button type="button" className="kalahaP2" > {gameState.players[1].pits[6].nrOfStones} </button> 
                    <button type="button" className="kalahaP1" > {gameState.players[0].pits[6].nrOfStones} </button> 
                </div>

                {/* Bowls Player 1 */}
                <div className="bowls">
                    {pitP1.map(pit => <button 
                        type="button" 
                        className="bowlP1" 
                        key={pit.index}
                        onClick={()=>playBowl(pit.index)}
                        > 
                            {pit.nrOfStones !=0 ? pit.nrOfStones : ""} 
                        </button>
                    )}
                </div>
                
                <p>{(gameState.players[0].hasTurn) ? `>>> ${gameState.players[0].name} <<<` : gameState.players[0].name}</p>
                <p>{(gameState.gameStatus.endOfGame) ? `Game is over. The winner is ${gameState.gameStatus.winner}.` : ''}</p>
                <button type="button" className="revenge" onClick={()=>rageQuit()}>RAGE QUIT</button>
            </div>
        </div>           
    )
}