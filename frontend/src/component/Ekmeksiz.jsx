import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import SignUpComponent from "./SignUpComponent";

export default class Ekmeksiz extends Component {
  render() {
    return (
      <div>
        <Router>
          <>
            <Switch>
              <Route path="/signup" component={SignUpComponent} />
            </Switch>
          </>
        </Router>
      </div>
    );
  }
}
