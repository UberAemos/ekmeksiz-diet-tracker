class LocalFoodDataService {
  addFood(dateName, course, food) {
    let date = this.getDailyDiet(dateName)
    for (let i = 0; i < date.courseList.length; i++) {
      if (date.courseList[i].name === course) {
        date.courseList[i].foodList.push(food);
      }
    }
    date = this.calculateTotal(date);
    date = JSON.stringify(date);
    sessionStorage.setItem(dateName, date);
  }

  getDailyDiet(dateName) {
    let date = sessionStorage.getItem(dateName);
    date = JSON.parse(date);
    return date;
  }

  deleteFood(dateName, courseName, foodName) {
    let date = this.getDailyDiet(dateName)
    for (let i = 0; i < date.courseList.length; i++) {
      let course = date.courseList[i]
      if (course.name === courseName) {
        for (let j = 0; j < course.foodList.length; j++){
          let food = course.foodList[j]
          if (food.name === foodName) {
            course.foodList.splice(j, 1)
          }
        }
      }
    }
    date = this.calculateTotal(date);
    return date
  }

  calculateTotal(date) {
    date.totalNutrients = {};
    for (let i = 0; i < 4; i++) {
      date.courseList[i].totalNutrients = {};
      for (let j = 0; j < date.courseList[i].foodList.length; j++) {
        let food = date.courseList[i].foodList[j];
        for (let k = 0; k < Object.keys(food.totalNutrients).length; k++) {
          let key = Object.keys(food.totalNutrients)[k];
          if (date.courseList[i].totalNutrients[key]) {
            date.courseList[i].totalNutrients[key] =
              date.courseList[i].totalNutrients[key] + food.totalNutrients[key];
          } else {
            date.courseList[i].totalNutrients[key] = food.totalNutrients[key];
          }
          if (date.totalNutrients[key]) {
            date.totalNutrients[key] = date.totalNutrients[key] + food.totalNutrients[key];
          } else {
            date.totalNutrients[key] = food.totalNutrients[key];
          }
        }
      }
    }
    return date;
  }
}

export default new LocalFoodDataService();