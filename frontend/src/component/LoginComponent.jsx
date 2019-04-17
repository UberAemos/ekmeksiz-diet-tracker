import React, { Component } from 'react'
import { Formik, Field, Form, ErrorMessage } from 'formik'

export default class LoginComponent extends Component {
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
