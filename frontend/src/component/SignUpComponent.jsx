import React, { Component } from 'react'
import { Formik, Field, Form, ErrorMessage } from 'formik'
import AuthenticationService from '../api/AuthenticationService'

export default class SignUpComponent extends Component {
  constructor(props) {
    super(props)

    this.createUser = this.createUser.bind(this)
    this.onSubmit = this.onSubmit.bind(this)
    this.validate = this.validate.bind(this)
  }

  createUser(values) {
    let role
    if (values.role) {
      role = ["pm"]
    } else {
      role = ["user"]
    }
    let user = {
      username: values.username,
      password: values.password,
      role: role,
      diet: JSON.parse(sessionStorage.getItem(sessionStorage.key(0)))
    }
    return user
  }

  onSubmit(values) {
    let user = this.createUser(values)
    AuthenticationService.createUser(user)
      .then(() => this.props.history.push('/login'))
    sessionStorage.clear()
  }

  validate(values) {
    let errors = {}
    if(!values.username) {
      errors.username = "Enter a Username"
    } else if (values.username.length < 6) {
      errors.username = "Enter a Username more than 6 characters"
    }

    if(!values.password) {
      errors.password = "Enter a Password"
    } else if (values.password.length < 6 || values.password.length > 12) {
      errors.password = "Enter a Password between 6 or 12 characters"
    }
    return errors
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
            <fieldset>
              <span>I want Premium features  </span>
              <Field type="checkbox" name="role"/>
            </fieldset>
            <button type="submit" className="btn btn-success mt-2">Sign Up</button>
          </Form>
        </Formik>
      </div>
    )
  }
}
