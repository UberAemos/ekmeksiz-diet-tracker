import React, { Component } from "react";
import MealCourse from "./MealCourse";
import FoodDataService from "../../api/FoodDataService";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import moment from "moment";

export default class HomeComponent extends Component {
  constructor(props) {
    if (props.username === "") {
      props.username = "Guest";
    }
    super(props);

    this.state = {
      date: ""
    }

    this.previousDay = this.previousDay.bind(this);
    this.nextDay = this.nextDay.bind(this);
    this.makeCourses = this.makeCourses.bind(this);
    this.getFoods = this.getFoods.bind(this)
  }

  componentWillMount() {
    let date = moment();
    this.getFoods(date)
  }

  previousDay() {
    let date = moment(this.state.date).subtract(1, "days")
    this.getFoods(date)
  }

  nextDay() {
    let date = moment(this.state.date).add(1, "days")
    this.getFoods(date)
  }

  makeCourses(courseList) {
    let courses = {
      breakfast: [],
      lunch: [],
      dinner: [],
      snacks: []
    };
    for (let i = 0; i < courseList.length; i++) {
      let course = courseList[i];
      courses[course.name] = course.foodList;
    }
    return courses;
  }

  getFoods(date) {
    FoodDataService.getFoods(
      this.props.match.params.username,
      moment(date).format("YYYYMMDD")
    ).then(response => {
      this.setState({
        courses: this.makeCourses(response.data),
        date
      })
    })
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
              {moment(this.state.date).format("dddd, MMMM Do, YYYY")}
            </span>
            <button className="btn btn-primary" onClick={this.nextDay}>
              <FontAwesomeIcon icon="caret-right" />
            </button>
          </div>
        </div>
        <table className="table-sm mx-1">
          <thead>
            <tr>
              <th className="border-bottom border-dark">Meal Name</th>
              <th className="border-bottom border-dark">Calories</th>
              <th className="border-bottom border-dark">Carbs</th>
              <th className="border-bottom border-dark">Fat</th>
              <th className="border-bottom border-dark">Protein</th>
              <th className="border-bottom border-dark">Sodium</th>
              <th className="border-bottom border-dark">Sugar</th>
            </tr>
          </thead>
          {(this.state.courses) &&
          <tbody>
            <MealCourse
              onDelete={() => this.getFoods(this.state.date)}
              courseName="breakfast"
              history={this.props.history}
              mealList={this.state.courses.breakfast}
              date={moment(this.state.date).format("YYYYMMDD")}
            />
            <MealCourse
              onDelete={() => this.getFoods(this.state.date)}
              history={this.props.history}
              courseName="lunch"
              mealList={this.state.courses.lunch}
              date={moment(this.state.date).format("YYYYMMDD")}
            />
            <MealCourse
              onDelete={() => this.getFoods(this.state.date)}
              history={this.props.history}
              courseName="dinner"
              mealList={this.state.courses.dinner}
              date={moment(this.state.date).format("YYYYMMDD")}
            />
            <MealCourse
              onDelete={() => this.getFoods(this.state.date)}
              courseName="snacks"
              history={this.props.history}
              mealList={this.state.courses.snacks}
              date={moment(this.state.date).format("YYYYMMDD")}
            />
            <tr>

            </tr>
          </tbody>
          }
        </table>
      </div>
    );
  }
}
