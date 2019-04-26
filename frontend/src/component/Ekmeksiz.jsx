import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import SignUpComponent from "./SignUpComponent";
import LoginComponent from "./LoginComponent"
import HomeComponent from "./home/HomeComponent"
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import AddFoodComponent from "./home/addFood/AddFoodComponent";
import WelcomeComponent from "./WelcomeComponent";

export default class Ekmeksiz extends Component {
  render() {
    return (
      <div className="d-flex w-100 h-100 mx-auto flex-column justify-content-center">
        <Router>
          <>
          <HeaderComponent />
            <Switch>
              <Route path="/signup" component={SignUpComponent} />
              <Route path="/login" component={LoginComponent}
              />
              <Route path="/:username/:date/:course/" component={AddFoodComponent} />
              <Route path="/:username" component={HomeComponent} />
              <Route path="/" component={WelcomeComponent} />
            </Switch>
            <FooterComponent />
          </>
        </Router>
      </div>
    );
  }
}
