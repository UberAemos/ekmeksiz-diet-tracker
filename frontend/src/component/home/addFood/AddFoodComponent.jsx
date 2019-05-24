import React, { Component } from "react";
import FoodDataService from "../../../api/FoodDataService";
import SearchFoodComponent from "./SearchFoodComponent"
import SelectFoodComponent from "./SelectFoodComponent";
import SelectedFoodComponent from "./SelectedFoodComponent";
import AuthenticationService from "../../../api/AuthenticationService";
import LocalFoodDataService from "../../../api/LocalFoodDataService";

export default class AddFood extends Component {
  constructor(props) {
    super(props);

    this.state = {
      selection: "",
      foods: ""
    };

    this.onSearchSubmit = this.onSearchSubmit.bind(this);
    this.onSelectSubmit = this.onSelectSubmit.bind(this);
    this.onSelectedSubmit = this.onSelectedSubmit.bind(this);
  }

  onSearchSubmit(foods) {
    this.setState({ foods })
  }

  onSelectSubmit(selection) {
    this.setState({ selection })
  }

  onSelectedSubmit(food, course) {
    if (AuthenticationService.isUserLoggedIn()) {
      FoodDataService.addFood(
        this.props.match.params.username,
        this.props.match.params.dateName,
        course,
        food
      ).then(() => this.props.history.push(`/${AuthenticationService.getLoggedInUsername()}`))
    } else {
      LocalFoodDataService.addFood(
        this.props.match.params.dateName,
        course,
        food
      )
      this.props.history.push(`/${AuthenticationService.getLoggedInUsername()}`)
    }
  }

  render() {
    return (
      <main className="h-75 w-75 mx-auto overflow-hidden d-flex flex-column my-2">
        <h3 className="d-flex">
          Add food to
          <span className="text-capitalize">&nbsp;{this.state.course}</span>
        </h3>
        <SearchFoodComponent onSubmit={this.onSearchSubmit} />
        {this.state.foods && (
          <div className="d-flex flex-column h-75">
            <h5 className="d-flex mb-2">Matching Foods: </h5>
            <div className="d-flex flex-row">
              <SelectFoodComponent foods={this.state.foods}
              onSubmit={this.onSelectSubmit} />
              {this.state.selection && 
                <SelectedFoodComponent selection={this.state.selection}
                onSubmit={this.onSelectedSubmit} />
            }
            </div>
          </div>
      )}
      </main>
    );
  }
}
