import './App.css';
import { CalculateScore } from './Components/CalculateScore';

function App() {
  return (
    <div>
      <CalculateScore
        Name={"Priya"}
        School={"St. Joseph's Public School"}
        total={312}
        goal={3.5}
      />
    </div>
  );
}

export default App;
