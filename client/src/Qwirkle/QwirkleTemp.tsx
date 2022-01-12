import React from "react"
import "./QwirkleTemp.css";
import confirm from './Buttons/Confirm.png'
import undo from './Buttons/Undo.png'
import trade from './Buttons/Trade.png'

class GridSquare extends React.Component {
    render() {
        return (
            <button className="gridSquare"></button>
        );
    }
}

class HandSquare extends React.Component {
    render() {
        return (
            <button className="handSquare"></button>
        );
    }
}

class Board extends React.Component {
    renderGridSquare() {
        return <GridSquare />;
    }

    render() {
        return (
            <div>
                <div className="board-row">
                    {this.renderGridSquare()}
                    {this.renderGridSquare()}    
                    {this.renderGridSquare()}
                </div>
                <div className="board-row">
                    {this.renderGridSquare()}
                    {this.renderGridSquare()}    
                    {this.renderGridSquare()}
                </div>
                <div className="board-row">
                    {this.renderGridSquare()}
                    {this.renderGridSquare()}    
                    {this.renderGridSquare()}
                </div>
            </div>
        );
    }
}

class Tilehand extends React.Component {
    renderHandSquare() {
        return <HandSquare />;
    }

    render() {
        return (
            <div>
                {this.renderHandSquare()}
                {this.renderHandSquare()}
                {this.renderHandSquare()}
                {this.renderHandSquare()}
                {this.renderHandSquare()}
                {this.renderHandSquare()}
            </div>      
        );
    }
}

class Buttons extends React.Component {
    render() {
        return (
            <div className="tiles">
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
        );
    }
}

class Game extends React.Component {
    render() {
        return (
            <div className="game">
                <div className="game-board">
                    <Board />
                </div>
                <div className="hand-buttons">
                <div className="tile-hand">
                    <Tilehand />
                </div>
                <div className="Buttons">
                    <Buttons />
                </div>
                </div>
            </div>
        );
    }
}

export function QwirkleTemp() {
    return (
    <div className="QwirkleBoard">
        <h1>Qwirkle!</h1>
        <Game />
    </div>
    )
}