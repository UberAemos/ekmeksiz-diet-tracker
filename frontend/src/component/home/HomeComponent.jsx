import React, { Component } from "react";
import MealCourse from "./MealCourse";
import FoodDataService from "../../api/FoodDataService";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import moment from "moment";
import AuthenticationService from "../../api/AuthenticationService";
import LocalFoodDataService from "../../api/LocalFoodDataService";
import { NUTRIENT_LABELS } from "../../Constants";

export default class HomeComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      date: "",
      error: ""
    };

    this.previousDay = this.previousDay.bind(this);
    this.nextDay = this.nextDay.bind(this);
    this.getDate = this.getDate.bind(this);
    this.deleteFood = this.deleteFood.bind(this)
    this.incrementFood = this.incrementFood.bind(this)
    this.subtractFood = this.subtractFood.bind(this)
  }

  componentDidMount() {
    let date = moment();
    this.getDate(date);
  }

  previousDay() {
    if (AuthenticationService.isUserLoggedIn()) {
      let date = moment(this.state.date.date).subtract(1, "days");
      this.getDate(date);
    } else {
      this.setState({
        error : "You have to login to create other diet plans"
      })
    }
  }

  nextDay() {
    if (AuthenticationService.isUserLoggedIn()) {
      let date = moment(this.state.date.date).add(1, "days");
      this.getDate(date);
    } else {
      this.setState({
        error : "You have to login to create other diet plans"
      })
    }
  }

  getDate(date) {
    let dateName = moment(date).format("YYYYMMDD");
    if (AuthenticationService.isUserLoggedIn()) {
      FoodDataService.getDailyDiet(this.props.match.params.username, dateName).then(
        response => {
          this.setState({
            date: response.data
          });
        }
      )
    } else {
      if (sessionStorage.getItem(dateName) !== null) {
        this.setState({
          date: LocalFoodDataService.getDailyDiet(dateName)
        });
      } else {
        FoodDataService.getDefaultDailyDiet(dateName).then(
          response => {
            this.setState({
              date: response.data
            });
            sessionStorage.setItem(dateName, JSON.stringify(response.data))
          }
        );
      }
    }
  }

  deleteFood(courseName, foodName, foodId) {
    if (AuthenticationService.isUserLoggedIn()) {
      FoodDataService.deleteFood(foodId).then(
        response => {
          this.setState({
            date: response.data
          })
        }
      )
    } else {
      let dateName = this.state.date.date
      let dateObj = LocalFoodDataService.deleteFood(dateName, courseName, foodName)
      this.setState({
        date : dateObj
      })
      dateObj = JSON.stringify(dateObj);
      sessionStorage.setItem(this.state.date.date, dateObj);
    }
  }

  subtractFood(courseName, foodId, foodName) {
    if (AuthenticationService.isUserLoggedIn()) {
      FoodDataService.subtractFood(foodId).then(
        response => {
          this.setState({
            date: response.data
          })
        }
      )
    } else {
      let dateObj = LocalFoodDataService.deleteFood(courseName, foodName)
      this.setState({
        date : dateObj
      })
      dateObj = JSON.stringify(dateObj);
      sessionStorage.setItem(this.state.date.date, dateObj);
    }
  }

  incrementFood(courseName, foodId, foodName) {
    if (AuthenticationService.isUserLoggedIn()) {
      FoodDataService.incrementFood(foodId).then(
        response => {
          this.setState({
            date: response.data
          })
        }
      )
    } else {
      let dateObj = LocalFoodDataService.deleteFood(courseName, foodName)
      this.setState({
        date : dateObj
      })
      dateObj = JSON.stringify(dateObj);
      sessionStorage.setItem(this.state.date.date, dateObj);
    }
  }

  render() {
    return (
      <div className="h-75 w-75 mx-auto overflow-auto d-flex flex-column my-2">
        <div className="d-flex flex-row align-items-center mb-2">
          <span className="text-primary font-weight-bold">
            Your Food Diary For:{" "}
          </span>
          <div className="mx-4">
            <button className="btn btn-primary" onClick={this.previousDay}>
              <FontAwesomeIcon icon="caret-left" />
            </button>
            <span className="mx-1 text-primary">
              {moment(this.state.date.date).format("dddd, MMMM Do, YYYY")}
            </span>
            <button className="btn btn-primary" onClick={this.nextDay}>
              <FontAwesomeIcon icon="caret-right" />
            </button>
          </div>
        </div>
        {(this.state.error) && (
            <span className="text-danger">{this.state.error}</span>
          )} 
        <table className="table-sm mx-1">
          <thead>
            <tr>
              <th className="border-bottom border-dark">Name</th>
              <th className="border-bottom border-dark">Calories</th>
              <th className="border-bottom border-dark">Carbs</th>
              <th className="border-bottom border-dark">Fat</th>
              <th className="border-bottom border-dark">Protein</th>
              <th className="border-bottom border-dark">Sugar</th>
              <th className="border-bottom border-dark"></th>
            </tr>
          </thead>
          {(this.state.date) && 
            <tbody>
              {this.state.date.courseList.map((course, index) => (
                <MealCourse
                  key = {index}
                  course= {course}
                  history={this.props.history}
                  date={moment(this.state.date.date).format("YYYYMMDD")}
                  onDelete = {this.deleteFood}
                  onIncrement = {this.incrementFood}
                  onSubtract = {this.subtractFood}
                />
              ))}
              {(Object.values(this.state.date.totalNutrients)[0] > 0) &&
                <tr>
                  <th className="text-primary font-weight-normal">Total: </th>
                  {NUTRIENT_LABELS.map(key => (
                    <th className="text-primary font-weight-normal">{this.state.date.totalNutrients[key]}</th>
                  ))}
                </tr>}
            </tbody>
          }
        </table>
      </div>
    );
  }
}