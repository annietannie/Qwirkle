import React from "react"

export function HowToPlay() {
    return <div className="Text">
        <h1>How to play</h1>
        <h3>Setup and starting the game</h3>
        <p>
            Each player receives 6 tiles from the bag, this is your hand. You can't see the tiles of other players and they can't see yours.
            The player with the largest number of tiles they have that share one attribute, eigher colour or shape &#40;duplicates are not included&#41; starts the game and then puts down this series. If there is a tie, the oldest player starts.
        </p>

        <h3>Play</h3>
        <p>
            On your turn, you can do two things:
            <ol>
                <li>Place one or more tiles to the grid and then draw tiles to bring your hand up to six</li>
                <li>Trade one or more tiles for different tiles</li>
            </ol>
        </p>

        <h3>Adding to the grid</h3>
        <p>
            A tile can only be placed horizontally or vertically adjacent to a tile already in the grid. 
            Two or more tiles that touch create a line. A line is either all one shape or all one colour. Tiles that are added to a line must share the same attribute as the tiles that are already in the line. Often there are places on the grid where no tile can be played.
            A line of shapes can only have one tile of each of the six colours and vice versa. For example, a line of squares can only have one blue square and a line of yellow can only have one yellow circle.
        </p>

        <h3>Trading in tiles</h3>
        <p>
            Another option is to trade some of your tiles, <b><i>instead</i></b> of adding to the grid. Set aside the tiles you want to trade in, then draw replacement tiles. Finally, mix the tiles that you traded back into the bag. If yo uare unable to add to the grid on your turn, you must trade in some or all of your tiles.
        </p>

        <h3>Scoring</h3>
        <p>
            When you create a line, you score on point for each tile in the line. Also, when you add to an existing line, you score one point for each tile in that line, including tiles that were already in that line. One thile can score two points if it is part of two different lines.
            You score an additional six points when you complete a line of six lines - this is called a Qwirkle. A Qwirkle scores at least twelve points - six for the six tiles and six for the bonus. The six tiles must be either six tiles of the same colour, each a different shape OR six tiles of the same shape, each a different colour. Lines of more than six tiles are not allowed.
            Whoever ends the game gets a six-point bonus.
        </p>

        <h3>Ending the game</h3>
        <p>
            When there are no more tiles to draw, play continues as before, but players do not replenish their hands at the end of their turn. The first player who uses all of their tiles ends the game and gets a six-point bonus. The player with the highest score wins.
        </p>
    </div>
}