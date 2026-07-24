import React from 'react';

class BookTicket extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      flightNo: 'AI-330',
      passengerName: '',
      booked: false
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleBooking = this.handleBooking.bind(this);
  }

  handleChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }

  handleBooking(event) {
    event.preventDefault();
    this.setState({ booked: true });
  }

  render() {
    return (
      <div>
        <h2>Book Your Ticket</h2>
        <form onSubmit={this.handleBooking}>
          <label>
            Passenger Name:
            <input
              type="text"
              name="passengerName"
              value={this.state.passengerName}
              onChange={this.handleChange}
            />
          </label>
          <br />
          <label>
            Select Flight:
            <select
              name="flightNo"
              value={this.state.flightNo}
              onChange={this.handleChange}
            >
              <option value="AI-330">AI-330 Hyderabad to Delhi</option>
              <option value="IN-512">IN-512 Hyderabad to Mumbai</option>
              <option value="SG-227">SG-227 Hyderabad to Bangalore</option>
            </select>
          </label>
          <br />
          <button type="submit">Book Ticket</button>
        </form>
        {this.state.booked && (
          <p>
            Ticket booked for {this.state.passengerName || 'Guest'} on flight{' '}
            {this.state.flightNo}!
          </p>
        )}
      </div>
    );
  }
}

export default BookTicket;
