import React, { Component } from 'react'
import {Formik, Form, ErrorMessage, Field} from 'formik'
import FoodDataService from '../../api/FoodDataService'

export default class AddFood extends Component {
    constructor(props) {
        super(props)

        this.state = {
            foods : []
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.addFood = this.addFood.bind(this)
    }

    onSubmit(values) {
        FoodDataService.searchFood(values.food).then(response => {this.setState(
            {
                foods : response.data  
            }
        )})
    }

    addFood(food) {
      console.log(food)
      FoodDataService.addFood(this.props.match.params.username, this.props.match.params.course, food)
    }

  render() {
    return (
      <div>
        <Formik
          initialValues={{
            food:""
          }}
          onSubmit={this.onSubmit}
          validateOnChange={false}
          validateOnBlur={false}
          validate={this.validate}
        >

          <Form className="form-signup w-25 mx-auto">
            <h1 className="h3 mb-3 font-weight-normal">Add food to {this.state.course}</h1>
            <ErrorMessage name="food" component="div" className="alert alert-warning" />
            <fieldset className="form-group">
              <Field className="form-control" type="text" name="food" placeholder="Recipe name" />
            </fieldset>
            <button type="submit" className="btn btn-success">Search</button>
            </Form>
          </Formik>
          <ul className="list-group">
          {
              this.state.foods.map((food, index) =>
                <button key={index} className="list-group-item list-group-item-action" onClick={() => this.addFood(food)}>{food.name}</button>
              )
          }
          </ul>
        </div>
    )
  }
}
