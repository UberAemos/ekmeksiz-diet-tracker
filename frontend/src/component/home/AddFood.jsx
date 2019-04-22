import React, { Component } from "react";
import { Formik, Form, ErrorMessage, Field } from "formik";
import FoodDataService from "../../api/FoodDataService";
import { TEST_FOOD_LIST } from "../../Constants";

export default class AddFood extends Component {
  constructor(props) {
    super(props);

    this.state = {
      course: this.props.match.params.course,
      foods: TEST_FOOD_LIST
    };

    this.onSubmit = this.onSubmit.bind(this);
    this.addFood = this.addFood.bind(this);
  }

  onSubmit(values) {
    FoodDataService.searchFood(values.food).then(response => {
      this.setState({
        foods: response.data
      });
    });
  }

  addFood(food) {
    FoodDataService.addFood(
      this.props.match.params.username,
      this.state.course,
      food
    );
  }

  render() {
    return (
      <main className="h-75 w-75 mx-auto overflow-hidden d-flex flex-column my-2">
        <h3 className="d-flex">
          Add food to
          <span className="text-capitalize">&nbsp;{this.state.course}</span>
        </h3>
        <Formik
          initialValues={{
            food: ""
          }}
          onSubmit={this.onSubmit}
          validateOnChange={false}
          validateOnBlur={false}
          validate={this.validate}
        >
          <Form className="form-signup w-50 h-25 mb-2">
            <h5 className="d-flex mb-2">Search our food database by name:</h5>
            <ErrorMessage
              name="food"
              component="div"
              className="alert alert-warning"
            />
            <div className="flex-row d-flex">
              <Field
                className="form-control mr-2"
                type="text"
                name="food"
                placeholder="Recipe name"
              />
              <button type="submit" className="btn btn-success">
                Search
              </button>
            </div>
          </Form>
        </Formik>
        {this.state.foods && (
          <div className="w-50 h-75 d-flex flex-column">
            <h5 className="d-flex mb-2">Matching Foods: </h5>
            <ul className="list-group overflow-auto mb-1 flex-shrink-1 border border-dark">
              {this.state.foods.hints.map((food, index) => (
                <button
                  key={index}
                  className="list-group-item list-group-item-action p-1"
                  onClick={() => this.addFood(food)}
                >
                  <div className="p-0">
                    <div className="p-0 text-primary text-capitalize d-flex">
                      {food.food.label}
                    </div>
                    <div className="text-secondary text-capitalize d-flex">
                      <span>{food.food.nutrients.ENERC_KCAL}&nbsp;</span>{" "}
                      calories
                    </div>
                  </div>
                </button>
              ))}
            </ul>
          </div>
        )}
      </main>
    );
  }
}
