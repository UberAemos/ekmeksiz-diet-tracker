import React, { Component } from 'react'
import { Formik, Field, Form, ErrorMessage } from 'formik'
import UserDataService from '../api/UserDataService'

export default class SignUpComponent extends Component {
  constructor(props) {
    super(props)

    this.createUser = this.createUser.bind(this)
    this.onSubmit = this.onSubmit.bind(this)
    this.validate = this.validate.bind(this)
  }

  createUser(values) {
    this.setState({hi : "hello"})
    let user = {
      username: values.username,
      password: values.password
    }
    return user
  }

  onSubmit(values) {
    let user = this.createUser(values)
    UserDataService.createUser(user)
      .then(() => this.props.history.push('/login'))
  }

  validate(values) {
    let user = this.createUser(values)

    return UserDataService.validateUser(user).then(response => {
      let errors = {}
      if(!values.username) {
        errors.username = "Enter a Username"
      } else if (values.username.length < 6 || values.username.length > 12) {
        errors.username = "Enter a Username between 6 or 12 characters"
      } else if (response.data) {
        errors.username = "Username exists"
      }
  
      if(!values.password) {
        errors.password = "Enter a Password"
      } else if (values.password.length < 6 || values.password.length > 12) {
        errors.password = "Enter a Password between 6 or 12 characters"
      }
      if (Object.keys(errors).length) {
        throw errors
      }
    })
  }

  render() {
    return (
      <div>
        <Formik
          onSubmit={this.onSubmit}
          validateOnChange={false}
          validateOnBlur={false}
          validate={this.validate}
        >

          <Form className="form-signup w-25 mx-auto">
            <h1 className="h3 mb-3 font-weight-normal">Sign Up</h1>
            <ErrorMessage name="username" component="div" className="alert alert-warning" />
            <ErrorMessage name="password" component="div" className="alert alert-warning" />
            <fieldset className="form-group">
              <Field className="form-control" type="text" name="username" placeholder="Username" />
            </fieldset>
            <fieldset className="form-group">
              <Field className="form-control" type="text" name="password" placeholder="Password" />
            </fieldset>
            <button type="submit" className="btn btn-success">Sign Up</button>
          </Form>
        </Formik>
      </div>
    )
  }
}
