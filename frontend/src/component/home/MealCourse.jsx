import React, { Component } from 'react'

export default class MealCourse extends Component {
    constructor(props) {
        super(props)
    }
  render() {
    return (
        <>
        <tr>
            <th className="table-course-name">{this.props.courseName}</th>
        </tr>
        {
            this.props.mealList.map (
                meal => 
                    <tr>
                        <td>{meal.name}</td>
                        <td>{meal.calory}</td>
                        <td>{meal.carbs}</td>
                        <td>{meal.fat}</td>
                        <td>{meal.protein}</td>
                        <td>{meal.sodium}</td>
                        <td>{meal.sugar}</td>
                    </tr>
            )
        }
        <tr className="table-button">
            <button className="btn btn-primary btn-small">Add Food</button>
        </tr>
        </>
    )
  }
}