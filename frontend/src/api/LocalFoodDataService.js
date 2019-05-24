class LocalFoodDataService {
  addFood(dateName, course, food) {
    let date = this.getDate(dateName)
    for (let i = 0; i < date.courses.length; i++) {
      if (date.courses[i].name === course) {
        food.course = date.courses[i].name;
        date.courses[i].foodList.push(food);
      }
    }
    date = this.calculateTotal(date);
    date = JSON.stringify(date);
    sessionStorage.setItem(dateName, date);
  }

  getDate(dateName) {
    let date = sessionStorage.getItem(dateName);
    date = JSON.parse(date);
    return date;
  }

  deleteFood(dateName, courseName, foodName) {
    let date = this.getDate(dateName)
    for (let i = 0; i < date.courses.length; i++) {
      let course = date.courses[i]
      if (course.name === courseName) {
        for (let j=0; j < course.foodList.length; j++){
          let food = course.foodList[j]
          if (food.name === foodName) {
            course.foodList.splice(j, 1)
          }
        }
      }
    }
    date = this.calculateTotal(date);
    date.naber = "naber"
    return date
  }

  calculateTotal(date) {
    date.total = {};
    for (let i = 0; i < 4; i++) {
      date.courses[i].total = {};
      for (let j = 0; j < date.courses[i].foodList.length; j++) {
        let food = date.courses[i].foodList[j];
        for (let k = 0; k < Object.keys(food.nutrition).length; k++) {
          let key = Object.keys(food.nutrition)[k];
          if (date.courses[i].total[key]) {
            date.courses[i].total[key] =
              date.courses[i].total[key] + food.nutrition[key];
          } else {
            date.courses[i].total[key] = food.nutrition[key];
          }
          if (date.total[key]) {
            date.total[key] = date.total[key] + food.nutrition[key];
          } else {
            date.total[key] = food.nutrition[key];
          }
        }
      }
    }
    return date;
  }
}

export default new LocalFoodDataService();
