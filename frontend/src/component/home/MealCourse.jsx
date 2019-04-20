import React, { Component } from "react";
import AuthenticationService from "../../api/AuthenticationService";

export default class MealCourse extends Component {
  constructor(props) {
    super(props);

    this.state = {
      course: this.props.courseName,
    };

    this.addFoodClicked = this.addFoodClicked.bind(this);
  }

  addFoodClicked(course) {
    this.props.history.push(AuthenticationService.getLoggedInUsername() + `/${course}`);
  }

  render() {
    return (
      <>
        <tr>
          <th className="table-course-name">{this.state.course}</th>
        </tr>
        {this.props.mealList.map(meal => (
          <tr>
            <td>{meal.name}</td>
            <td>{meal.calory}</td>
            <td>{meal.carbs}</td>
            <td>{meal.fat}</td>
            <td>{meal.protein}</td>
            <td>{meal.sodium}</td>
            <td>{meal.sugar}</td>
          </tr>
        ))}
        <tr className="table-button">
          <button
            className="btn btn-primary btn-small"
            onClick={() => this.addFoodClicked(this.state.course)}
          >
            Add Food
          </button>
        </tr>
      </>
    );
  }
}
