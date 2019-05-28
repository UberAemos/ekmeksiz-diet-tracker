import React, { Component } from 'react'
import { Formik, Field, Form, ErrorMessage } from 'formik'
import AuthenticationService from '../api/AuthenticationService';

export default class LoginComponent extends Component {
  constructor(props) {
    super(props)

    this.createForm = this.createForm.bind(this)
    this.onSubmit = this.onSubmit.bind(this)
  }

  createForm(values) {
    let form = {
      username: values.username,
      password: values.password
    }
    return form
  }

  onSubmit(values) {
    let form = this.createForm(values)
    AuthenticationService.loginUser(form)
      .then(response => {AuthenticationService.registerSuccessfulLogin(values.username, response.data.accessToken)
        if (response.data.isAdmin) {
          window.location.pathname = "/admin"
        } else {
          window.location.pathname = "/"
        }
      })
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
            //validateOnChange={false}
            //validateOnBlur={false}
            //validate={this.validate}
          >
  
            <Form className="form-signup w-25 mx-auto">
              <h1 className="h3 mb-3 font-weight-normal">Login</h1>
              <ErrorMessage name="username" component="div" className="alert alert-warning" />
              <ErrorMessage name="password" component="div" className="alert alert-warning" />
              <fieldset className="form-group">
                <Field className="form-control" type="text" name="username" placeholder="Username" />
              </fieldset>
              <fieldset className="form-group">
                <Field className="form-control" type="text" name="password" placeholder="Password" />
              </fieldset>
              <button type="submit" className="btn btn-success">Login</button>
            </Form>
          </Formik>
        </div>
      )
  }
}
