import React from 'react';
import logo from './logo.svg';
import './App.css';
import {useRecoilState} from "recoil";
import {greetingState} from "./GreetingStore";

function App() {
    const [greeting, setGreeting] = useRecoilState(greetingState)

    const getGreetingMessage = async () => {
        await fetch("/greeting")
            .then(response => response.text())
            .then(text => setGreeting(text))
            .catch(error => console.log(error))
    }

    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
                <p onClick={getGreetingMessage}>
                    {greeting} React
                </p>
                <a
                    className="App-link"
                    href="https://reactjs.org"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Learn React
                </a>
            </header>
        </div>
    );
}

export default App;
