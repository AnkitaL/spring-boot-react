import React from "react";
import UserService from "F:/reactJS/java-spring-data/src/Service/UserService.js";

class UserComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      users: [],
    };

    this.updateUserClicked = this.updateUserClicked.bind(this);
    this.deleteUserClicked = this.deleteUserClicked.bind(this);
    this.addUserClicked = this.addUserClicked.bind(this);
    this.refreshCourses = this.refreshCourses.bind(this);
  }

  componentDidMount() {
    this.refreshCourses();
  }
  refreshCourses() {
    UserService.getUsers().then((response) => {
      this.setState({ users: response.data });
    });
  }

  deleteUserClicked(id) {
    UserService.deleteUser(id).then((response) => {
      this.setState({ message: `Delete of course ${id} Successful` });
      this.componentDidMount();
    });
  }

  addUserClicked() {
    this.props.history.push(`/users/-1`);
  }

  updateUserClicked(id) {
    console.log("update " + id);
    this.props.history.push(`/users/${id}`);
  }
  render() {
    return (
      <div>
        <h1 className='text-center'>Users List</h1>
        <table className='table table-strip'>
          <thead>
            <tr>
              <td>user ID</td>
              <td>first name</td>
              <td>last name</td>
              <td>email id</td>
              <td>Delete</td>
              <td>Update</td>
            </tr>
          </thead>
          <tbody>
            {this.state.users.map((user) => (
              <tr key={user.userId}>
                <td>{user.userId}</td>
                <td>{user.firstName}</td>
                <td>{user.lastName}</td>
                <td>{user.email}</td>
                <td>
                  <button
                    className='btn btn-warning'
                    onClick={() => this.deleteUserClicked(user.userId)}
                  >
                    Delete
                  </button>
                </td>
                <td>
                  <button
                    className='btn btn-success'
                    onClick={() => this.updateUserClicked(user.userId)}
                  >
                    Update
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div>
          <button className='btn btn-success' onClick={this.addUserClicked}>
            Add
          </button>
        </div>
      </div>
    );
  }
}

export default UserComponent;
