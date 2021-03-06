import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import SignUpComponent from "./SignUpComponent";
import LoginComponent from "./LoginComponent"
import HomeComponent from "./home/HomeComponent"
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import AddFoodComponent from "./home/addFood/AddFoodComponent";
import WelcomeComponent from "./WelcomeComponent";
import AdminComponent from "./AdminComponent";
import AuthenticationService from "../api/AuthenticationService";

export default class Ekmeksiz extends Component {
  render() {
    AuthenticationService.setupAxiosInterceptors()
    return (
      <div className="d-flex w-100 h-100 mx-auto flex-column justify-content-center">
        <Router>
          <>
          <HeaderComponent />
            <Switch>
              <Route path="/admin" component={AdminComponent} />
              <Route path="/signup" component={SignUpComponent} />
              <Route path="/login" component={LoginComponent}
              />
              <Route path="/:username/:dateName/:course/" component={AddFoodComponent} />
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
