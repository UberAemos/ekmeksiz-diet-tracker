import React, { Component } from "react";
import AuthenticationService from "../../api/AuthenticationService";
import { NUTRIENT_LABELS } from "../../Constants";

export default class MealCourse extends Component {
  constructor(props) {
    super(props);

    this.state = {
      course: this.props.courseName,
    };

    this.addFoodClicked = this.addFoodClicked.bind(this);
  }

  addFoodClicked(course) {
    let username = AuthenticationService.getLoggedInUsername()
    let date = this.props.date
    this.props.history.push(`${username}/${date}/${course}`);
  }

  render() {
    return (
      <>
        <tr>
          <th className="text-capitalize">{this.state.course}</th>
        </tr>
        {this.props.mealList.map((meal, index) => (
          <tr key={index}>
            <td className="text-capitalize">{meal.name}</td>
            {NUTRIENT_LABELS.map((label,index) =>
              {return <td key={index}>{meal.nutrition[label]}</td>}
              )}
          </tr>
        ))}
        {this.props.mealList.length > 1 && 
          <tr className="text-primary">
            <td>Total:</td>
            {NUTRIENT_LABELS.map((key, index) => {
              console.log(key)
              let total = 0
              for (let i=0;i<this.props.mealList.length;i++) {
                let meal = this.props.mealList[i]
                total += meal.nutrition[key]
                console.log(total)
              }
              return <td key={index}>{total}</td>
              })
            }
          </tr>
        }
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
