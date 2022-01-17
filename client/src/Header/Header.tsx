import React from "react";
import { Link } from "react-router-dom";
import "./Header.css";
import urlLogo from "./QwirkleLogo.png";

/**
 * A Header component with a Sogyo logo, the name of the application and several links to different pages
 */
export function Header() {
    return <header className="main-header">
        <div className="main-title">
            <img src={urlLogo} /> 
        </div>
        <div className="main-navigation">
            <Link to="/">Play</Link>
            <Link to="/howToPlay">How to Play</Link>
            {/* <Link to="/qwirkletemp">Qwirkle template</Link> */}
        </div>
    </header>
}