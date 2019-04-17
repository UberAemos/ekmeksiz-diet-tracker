import React, { Component } from 'react'
import { Link } from "react-router-dom";

export default class HeaderComponent extends Component {
  render() {
    return (
        
      <header className="navbar navbar-expand-lg navbar-dark bg-dark">
      <ul className="navbar-nav">
      <li className="nav-item">
      <a className="navbar-brand" href="/home">Ekmeksiz</a>
      </li>
      <li className="nav-item">
      <Link className="nav-link" to="/home">Home</Link>
      </li>
      <li className="nav-item">
      <Link className="nav-link" to="/recipe">Recipes</Link>
      </li>
      <li className="nav-item">
      <Link className="nav-link" to="/report">Report</Link>
      </li>
      </ul>
      <ul className="navbar-nav navbar-collapse justify-content-end">
      <li className="nav-item mr-4">
      <span className="navbar-text">Hello, Guest</span>
      </li>
      <li className="nav-item">
      <Link className="nav-link" to="/login">Login</Link>
      </li>
      <li className="nav-item">
      <Link className="nav-link" to="/logout">Logout</Link>
      </li>
      </ul>
      </header>
    )
  }
}
