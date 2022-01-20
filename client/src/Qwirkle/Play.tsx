import React, { useState } from "react"
import "./Play.css";
import confirm from './Buttons/Confirm.png'
import undo from './Buttons/Undo.png'
import trade from './Buttons/Trade.png'
import bag from './Buttons/TileBag.png'
import type { GameState } from "src/gameState";

type PlayProps = {
    gameState: GameState;
    setGameState(newGameState: GameState): void;
}

export function Play({ gameState, setGameState}: PlayProps) {
    const handPlayer1 = gameState.players[0].tiles;
    const board = gameState.gameBoard.tiles;
    var yourTurn: boolean = gameState.players[0].hasTurn;
    var selectedTile: number | null = null;
    var player: number = 0;

    function selectTile(index: number) {
        if (handPlayer1[index] != null) {
            selectedTile = index;
        }
    }

    async function placeTile(y: number, x: number) {
        if (selectedTile != null) {
            try {
                const response = await fetch('qwirkle/api/playtile', {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ player, selectedTile, x, y })
                });
                
                //console.log("Player: " + player + " index: " + selectedTile + " x: " + x + " y: " + y);

                if (response.ok) {
                    const gameState = await response.json();
                    //console.log(gameState);
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
    }

    async function tradeTile() {
        if (selectedTile != null) {
            try {
                const response = await fetch('qwirkle/api/trade', {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    // for now default player is player 1
                    body: JSON.stringify({ player, selectedTile })
                });

                if (response.ok) {
                    //console.log("let's trade tile " + selectedTile);
                    const gameState = await response.json();
                    //console.log(gameState);
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
    }

    async function restart() {
        localStorage.clear();
        setGameState(undefined);
        window.location.reload(false);
    }

    async function confirmTurn() {
        try {
            const response = await fetch('qwirkle/api/confirmturn', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                // for now default player is player 1
                body: JSON.stringify(0)
            });

            if (response.ok) {
                const gameState = await response.json();
                //console.log(gameState);
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

    async function cancelTurn() {
        try {
            const response = await fetch('qwirkle/api/cancelturn', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                // for now default player is player 1
                body: JSON.stringify(0)
            });

            if (response.ok) {
                const gameState = await response.json();
                //console.log(gameState);
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

    return (
    <div className="QwirkleBoard">

        <h1>{ yourTurn ? `Your turn!` : `Not your turn`} </h1>
        <h1>{gameState.isGameOver ? `The game is over` : ``} </h1>
        <h2>Your score: {gameState.players[0].score}</h2>

        <div className="gameBoard">
        {board.map((tileRow, i) => {
                return (
                    <div
                        className="boardRow" key={i}>
                        {tileRow.map((tile, j) => {
                            if (tile == null) {
                                return (
                                <button 
                                    id="emptyGridTile" 
                                    className="tile"
                                    key={j}
                                    onClick={() => placeTile(i, j)}
                                ></button>
                                )
                            } else {
                                return (
                                <img
                                    className="tile"
                                    key={j}
                                    draggable="false"
                                    src={"./Tiles/" + tile.shape + "_" +  tile.colour + ".png"}
                                ></img>
                                )
                            }
                        })}
                    </div>
                )
            })}
        </div>

        <div className="hand-player">
            {handPlayer1.map((tile, j) => {
                if(tile == null) {
                    return (
                        <div
                            id="emptyHandTile"
                            className="tile"
                            key={j}>
                        </div>
                    )
                } else {
                    return (
                        <input
                            id="handTile"
                            className="tile"
                            draggable="true"
                            type="image"
                            key={j}
                            src={"./Tiles/" + tile.shape + "_" +  tile.colour + ".png"}
                            onClick={() => selectTile(j)}
                        ></input>
                    )
                }
            })}
        </div>

        <div className="buttons">
                <div id="confirm" className="button">
                    <img 
                        src={confirm}
                        draggable="false"
                        onClick={() => confirmTurn()}
                    ></img>
                </div>
                <div id="trade" className="button">
                    <img 
                        src={trade}
                        draggable="false"
                        onClick={() => tradeTile()}
                    ></img>
                </div>
                <div id="undo" className="button">
                    <img 
                        src={undo}
                        draggable="false"
                        onClick={() => cancelTurn()}
                    ></img>
                </div>
            </div>

        <div className="bagContainer">
            <img
                className="tileBag" 
                src={bag} />
            <div className="tilesLeft">{gameState.numberOfTilesLeft}</div>
        </div>

        <button type="button" className="restart" onClick={() => restart()}>Restart</button>
        </div>
    )
}