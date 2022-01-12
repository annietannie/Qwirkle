import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Header } from "./Header/Header";
import { HowToPlay } from "./HowToPlay/HowToPlay";
import { Mancala } from "./Mancala/Mancala";
import { QwirkleTemp } from "./Qwirkle/QwirkleTemp";
import "./App.css";

export function App() {
    return (
        <Router>
            {/* The header with navigation options is always on top of every page */}
            <Header />

            <div className="main-content">
                <Switch>
                    {/* If the user goes to the url /about, show the about page */}
                    <Route path="/howToPlay">
                        <HowToPlay />
                    </Route>

                    <Route path="/qwirkletemp">
                        <QwirkleTemp />
                    </Route>

                    {/* If the user goes to any other url, show the play page */}
                    <Route path="/">
                        <Mancala />
                    </Route>
                </Switch>
            </div>
        </Router>
    )
}