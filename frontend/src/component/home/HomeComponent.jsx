import React, { Component } from 'react'
import MealCourse from './MealCourse'
import FoodDataService from '../../api/FoodDataService';


export default class HomeComponent extends Component {
    constructor(props) {
      if (props.username === "") {
        props.username = "Guest"
      }
        super(props)

        this.state = {
            date : "",
            courses : {
              breakfast: [],
              lunch: [],
              dinner: [],
              snack: []
            }
        }
    }

    componentDidMount() {
      FoodDataService.getFoods(this.props.match.params.username)
      .then(response => {this.setState(
        {
          courses : {
            breakfast : response.data,
            lunch : [],
            dinner : [],
            snack : []
          }
        }
      )})
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
          <MealCourse courseName="breakfast" history={this.props.history} mealList={this.state.courses.breakfast} />
          <MealCourse history={this.props.history} courseName="lunch" mealList={this.state.courses.lunch} />
          <MealCourse history={this.props.history} courseName="dinner" mealList={this.state.courses.dinner} />
          <MealCourse courseName="snacks" history={this.props.history} mealList={this.state.courses.snack} />
        </tbody>
        </table>
      </div>
    )
  }
}
