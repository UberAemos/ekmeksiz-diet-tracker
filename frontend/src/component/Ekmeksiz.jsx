import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import SignUpComponent from "./SignUpComponent";
import LoginComponent from "./LoginComponent"
import HomeComponent from "./home/HomeComponent"
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";

export default class Ekmeksiz extends Component {
  render() {
    return (
      <div>
        <Router>
          <>
          <HeaderComponent />
            <Switch>
              <Route path="/signup" component={SignUpComponent} />
              <Route path="/login" component={LoginComponent}
              />
              <Route path="/home" component={HomeComponent} />
            </Switch>
            <FooterComponent />
          </>
        </Router>
      </div>
    );
  }
}
