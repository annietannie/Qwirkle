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
    var colourMode: String = "Colour Blind";

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
                
                if (response.ok) {
                    const gameState = await response.json();
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
                    const gameState = await response.json();
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

    function colourBlind() {
        colourMode = "normal colour";
        window.location.reload(false);
    }

    // This function will be triggered when you start dragging
    function dragStartHandler (
        event: React.DragEvent<HTMLDivElement>,
        index: number) 
        {
        event.dataTransfer.setData("text", index.toString());
    };

    // This makes the third box become droppable
    function allowDrop (event: React.DragEvent<HTMLDivElement>) {
        event.preventDefault();
    };

    // This function will be triggered when dropping
    function dropHandler (event: React.DragEvent<HTMLDivElement>) {
        event.preventDefault();
        const data = event.dataTransfer.getData("text");
        placeTile()
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
                                <div 
                                    className="tile emptyTile emptyGridTile"
                                    onDragOver={allowDrop}
                                    onDrop={dropHandler}
                                    key={j}
                                    onClick={() => placeTile(i, j)}
                                ></div>
                                )
                            } else {
                                return (
                                <img
                                    className="tile"
                                    key={j}
                                    draggable="false"
                                    src={"./SVG_Tiles/" + tile.shape + "_" +  tile.colour + ".SVG"}
                                ></img>
                                )
                            }
                        })}
                    </div>
                )
            })}
        </div>

        <div id="tools">
        <div className="hand-player">
            {handPlayer1.map((tile, j) => {
                if(tile == null) {
                    return (
                        <div
                            className="tile emptyTile emptyHandTile"
                            key={j}>
                        </div>
                    )
                } else {
                    return (
                        <input
                            id={j.toString()}
                            className="tile handTile"
                            draggable={true}
                            onDragStart={(event) => dragStartHandler(event, j)}
                            type="image"
                            key={j}
                            src={"./SVG_Tiles/" + tile.shape + "_" +  tile.colour + ".SVG"}
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
        </div>

        <div className="bagContainer">
            <img
                className="tileBag" 
                src={bag} />
            <div className="tilesLeft">{gameState.numberOfTilesLeft}</div>
        </div>

        <button type="button" id="restart" onClick={() => restart()}>Restart</button>
        
        </div>
    )
}


