import React, { Component } from 'react'
import MealCourse from './MealCourse'


export default class HomeComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            date : "",
            breakfast: [],
            lunch: [],
            dinner: [],
            snack: []
        }
    }
  render() {
    return (
      <div>
<table className="table">
        <thead>
          <tr>
            <th>Meal Name</th>
            <th>Calories</th>
            <th>Carbs</th>
            <th>Fat</th>
            <th>Protein</th>
            <th>Sodium</th>
            <th>Sugar</th>
          </tr>
        </thead>
        <tbody>
          <MealCourse courseName="breakfast" mealList={this.state.breakfast} />
          <MealCourse courseName="lunch" mealList={this.state.lunch} />
          <MealCourse courseName="dinner" mealList={this.state.dinner} />
          <MealCourse courseName="snacks" mealList={this.state.snack} />
        </tbody>
        </table>
      </div>
    )
  }
}
