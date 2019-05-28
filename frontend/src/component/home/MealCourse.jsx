import React, { Component } from "react";
import AuthenticationService from "../../api/AuthenticationService";
import { NUTRIENT_LABELS } from "../../Constants";

export default class MealCourse extends Component {
  constructor(props) {
    super(props);
    this.state = {
      course: this.props.course
    };

    this.addFoodClicked = this.addFoodClicked.bind(this);
    this.deleteFoodClicked = this.deleteFoodClicked.bind(this);
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
      course: nextProps.course
    });
  }

  addFoodClicked(course) {
    let username = AuthenticationService.getLoggedInUsername();
    let dateName = this.props.date;
    let courseName = this.state.course.name;
    this.props.history.push(`${username}/${dateName}/${courseName}`);
  }

  deleteFoodClicked(id, name) {
    let username = AuthenticationService.getLoggedInUsername();
    let date = this.props.date;
    let course = this.state.course.name;
    this.props.onDelete(username, date, course, id, name);
  }

  render() {
    return (
      <>
        <tr>
          <th className="text-capitalize">{this.state.course.name}</th>
        </tr>
        {this.state.course.foodList.map((meal, index) => (
          <tr key={index}>
            <td>
              <div className="d-flex flex-row align-items-center justify-content-center">
                <button
                  className="btn btn-danger"
                  onClick={() => this.deleteFoodClicked(meal.id, meal.name)}
                >
                  -
                </button>
                <span className="text-capitalize">{meal.name}</span>
              </div>
            </td>
            {NUTRIENT_LABELS.map((label, index) => {
              return <td key={index}>{meal.nutrition[label]}</td>;
            })}
          </tr>
        ))}
        {(this.state.course.foodList).length > 1 && (
          <tr>
            <th className="text-primary font-weight-normal">Total: </th>
            {NUTRIENT_LABELS.map(key => (
              <th className="text-primary font-weight-normal">
                {this.state.course.total[key]}
              </th>
            ))}
          </tr>
        )}
        <tr className="d-table-row">
          <td className="border-bottom">
            <button
              className="btn btn-primary btn-small"
              onClick={() => this.addFoodClicked(this.state.course)}
            >
              Add Food
            </button>
          </td>
          <td className="border-bottom" />
          <td className="border-bottom" />
          <td className="border-bottom" />
          <td className="border-bottom" />
          <td className="border-bottom" />
          <td className="border-bottom" />
        </tr>
      </>
    );
  }
}
