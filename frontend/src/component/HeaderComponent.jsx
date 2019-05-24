import React, { Component } from 'react'
import { Link } from "react-router-dom";
import AuthenticationService from '../api/AuthenticationService'

export default class HeaderComponent extends Component {
  
  render() {
    let username = AuthenticationService.getLoggedInUsername()
    return (
        
      <header className="navbar navbar-expand-lg navbar-dark bg-dark mb-auto">
      <ul className="navbar-nav">
      <li className="nav-item">
      <a className="navbar-brand" href={"/"}>Ekmeksiz</a>
      </li>
      <li className="nav-item">
      <Link className="nav-link" to={"/"}>Home</Link>
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
      <span className="navbar-text">Hello, {username}</span>
      </li>
      {!AuthenticationService.isUserLoggedIn() &&
        <li className="nav-item">
          <Link className="nav-link" to="/login">Login</Link>
        </li>
      }
      {!AuthenticationService.isUserLoggedIn() &&
        <li className="nav-item">
          <Link className="nav-link" to="/signup">Sign Up</Link>
        </li>
      }
      {AuthenticationService.isUserLoggedIn() &&
        <li className="nav-item">
          <Link className="nav-link" to="/logout">Logout</Link>
        </li>
      }
      </ul>
      </header>
    )
  }
}
