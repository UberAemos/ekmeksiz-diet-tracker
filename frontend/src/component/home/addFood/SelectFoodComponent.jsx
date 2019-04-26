import React, { Component } from "react";

export default class SelectFoodComponent extends Component {
  constructor(props) {
    super(props)

    this.state = {
      foods: this.props.foods,
      selection: ""
    }

    this.selectFood = this.selectFood.bind(this)
  }

  selectFood(food) {
    this.setState({ selection: food })
    const { onSubmit } = this.props

    onSubmit(food)
  }

  render() {
    return (
      <ul className="w-50 h-100 list-group overflow-auto mb-1 flex-shrink-1 border border-dark mr-2">
        {this.state.foods.hints.map((food, index) => (
          <button
            key={index}
            className="list-group-item list-group-item-action p-1"
            onClick={() => this.selectFood(food)}
          >
            <div className="p-0">
              <div className="p-0 text-primary text-capitalize d-flex">
                {food.food.label}
              </div>
              <div className="text-secondary text-capitalize d-flex">
                <span>{food.food.nutrients.ENERC_KCAL}&nbsp;</span> calories
              </div>
            </div>
          </button>
        ))}
      </ul>
    );
  }
}
