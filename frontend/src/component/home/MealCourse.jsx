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
    this.incrementFoodClicked = this.incrementFoodClicked.bind(this);
    this.subtractFoodClicked = this.subtractFoodClicked.bind(this);
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
      course: nextProps.course
    });
  }

  addFoodClicked() {
    let username = AuthenticationService.getLoggedInUsername()
    let dateName = this.props.date
    let courseName = this.state.course.name
    this.props.history.push(
      {
        pathname: `${username}/${dateName}/${courseName}`,
        state: {
          course: this.state.course
        }
      }
    )
  }

  deleteFoodClicked(foodName, foodId) {
    let courseName = this.state.course.name;
    this.props.onDelete(courseName, foodName, foodId);
  }

  incrementFoodClicked(foodId, foodName) {
    let courseName = this.state.course.name;
    this.props.onIncrement(courseName, foodId, foodName);
  }

  subtractFoodClicked(foodId, foodName) {
    let courseName = this.state.course.name;
    this.props.onSubtract(courseName, foodId, foodName);
  }

  render() {
    return (
      <>
        <tr>
          <th className="text-capitalize">{this.state.course.name}</th>
        </tr>
        {this.state.course.foodList.map((food, index) => (
          <tr key={index}>
            <td className="text-capitalize">{food.name}, {food.measure}, {food.quantity}</td>
            {NUTRIENT_LABELS.map((label, index) => {
              return <td key={index}>{food.totalNutrients[label]}</td>;
            })}
            <td>
              <div>
                <button
                  className="btn btn-sm btn-success"
                  onClick={() => this.incrementFoodClicked(food.id, food.name)}
                >
                +
                </button>
                <button
                  className="btn btn-sm btn-danger"
                  onClick={() => this.subtractFoodClicked(food.id, food.name)}
                >
                -
                </button>
                <button
                  className="btn btn-sm btn-warning"
                  onClick={() => this.deleteFoodClicked(food.name, food.id)}
                >
                x
                </button>
              </div>
            </td>
          </tr>
        ))}
        {(this.state.course.foodList).length > 1 && (
          <tr>
            <th className="text-primary font-weight-normal">Total: </th>
            {NUTRIENT_LABELS.map(key => (
              <th className="text-primary font-weight-normal">
                {this.state.course.totalNutrients[key]}
              </th>
            ))}
          </tr>
        )}
        <tr className="d-table-row">
          <td className="border-bottom">
            <button
              className="btn btn-primary btn-small"
              onClick={() => this.addFoodClicked()}
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
