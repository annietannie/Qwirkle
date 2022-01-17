import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Header } from "./Header/Header";
import { HowToPlay } from "./HowToPlay/HowToPlay";
import { Qwirkle } from "./Qwirkle/Qwirkle";
import "./App.css";

export function App() {
    return (
        <Router>
            {/* The header with navigation options is always on top of every page */}
            <Header />

            <div className="main-content">
                <Switch>
                    {/* If the user goes to the url /about, show the about page */}
                    <Route path="/howtoplay">
                        <HowToPlay />
                    </Route>

                    <Route path="/">
                        <Qwirkle />
                    </Route>
                </Switch>
            </div>
        </Router>
    )
}