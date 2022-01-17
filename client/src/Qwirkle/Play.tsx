import React, { useState } from "react"
import "./Play.css";
import confirm from './Buttons/Confirm.png'
import undo from './Buttons/Undo.png'
import trade from './Buttons/Trade.png'
import type { GameState } from "src/gameState";

type PlayProps = {
    gameState: GameState;
    setGameState(newGameState: GameState): void;
}

function Counter() {
    const [count, setCount] = useState(0);

    const addOne = async () => {
        const response = await fetch('qwirkle/api/addone', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ count: count })
        });


        if (response.ok) {
            const countState = await response.json();
            setCount(countState.count);
        }
        
    }

    return (
        <div>
            <p>You clicked {count} times</p>
            <button 
                type="button"
                onClick={addOne}
            >+ 1</button>
        </div>
    )
}

export function Play({ gameState, setGameState}: PlayProps) {
    const handPlayer1 = gameState.players[0].tiles;
    const board = gameState.gameBoard.tiles;

    function sayHello() {
        console.log("Click");
    }

    return (
    <div className="QwirkleBoard">

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
                                    type="button"
                                    key={j}
                                    onClick={sayHello}
                                ></button>
                                )
                            } else {
                                return (
                                <img
                                    className="tile"
                                    key={j}
                                    src={"./Tiles/" + tile.shape + "_" +  tile.colour + ".png"}
                                ></img>
                                )
                            }
                        })}
                    </div>
                )
            })}
        </div>

        <div></div>

        <div className="hand-player1">
            {handPlayer1.map((tile, j) => {
                if(tile == null) {
                    return (
                        <div
                            id="emptyHandTile"
                            className="tile"
                            key={j} />
                    )
                } else {
                    return (
                        <input
                            id="handTile"
                            className="tile"
                            type="image"
                            key={j}
                            src={"./Tiles/" + tile.shape + "_" +  tile.colour + ".png"}
                            onClick={sayHello}
                        ></input>
                    )
                }
            })}
        </div>

        <div className="buttons">
                <div id="confirm" className="button">
                    <img src={confirm}></img>
                </div>
                <div id="trade" className="button">
                    <img src={trade}></img>
                </div>
                <div id="undo" className="button">
                    <img src={undo}></img>
                </div>
            </div>

        <div className="TileBag">
            <p>Tiles left in tile bag: {gameState.numberOfTilesLeft}</p>
        </div>
        
        <Counter />        
    </div>
    )
}