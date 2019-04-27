import React, { Component } from "react";
import AuthenticationService from "../../api/AuthenticationService";
import { NUTRIENT_LABELS } from "../../Constants";
import FoodDataService from "../../api/FoodDataService";

export default class MealCourse extends Component {
  constructor(props) {
    super(props);

    this.state = {
      course: this.props.courseName,
      totalObj: [],
      mealList: ""
    };

    this.addFoodClicked = this.addFoodClicked.bind(this);
    this.deleteFoodClicked = this.deleteFoodClicked.bind(this);
    this.calculateTotal = this.calculateTotal.bind(this);
  }

  componentWillMount() {
    this.setState({
      mealList: this.props.mealList
    })
    this.calculateTotal()
  }

  addFoodClicked(course) {
    let username = AuthenticationService.getLoggedInUsername()
    let date = this.props.date
    this.props.history.push(`${username}/${date}/${course}`);
  }

  calculateTotal() {
    let totalObj = []
    if (this.props.mealList.length > 0) {
      for (let i=0; i < NUTRIENT_LABELS.length; i++) {
        let total = 0
        let label = NUTRIENT_LABELS[i]
        for (let j=0;j<this.props.mealList.length;j++) {
          let meal = this.props.mealList[j]
          total += meal.nutrition[label]
        }
        totalObj.push(total)
      }
    }
    this.setState({
      totalObj
    })
  }

  deleteFoodClicked(id) {
    const { onDelete } = this.props
    let username = AuthenticationService.getLoggedInUsername()
    let date = this.props.date
    let course = this.state.course
    FoodDataService.deleteFood(username, date, course, id).then(response => {
        onDelete()
    })
  }

  render() {
    return (
      <>
        <tr>
          <th className="text-capitalize">{this.state.course}</th>
        </tr>
        {this.props.mealList.map((meal, index) => (
          <tr key={index}>
            <td>
              <div className="d-flex flex-row align-items-center justify-content-center">
                <button className="btn btn-danger" onClick={() => this.deleteFoodClicked(meal.id)}>-</button>
                <span className="text-capitalize">{meal.name}</span>
              </div></td>
            {NUTRIENT_LABELS.map((label,index) =>
              {return <td key={index}>{meal.nutrition[label]}</td>}
              )}
          </tr>
        ))}
        {this.props.mealList.length > 1 && 
          <tr className="text-primary">
            <td>Total:</td>
            {this.state.totalObj.map((total, index) => {
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
