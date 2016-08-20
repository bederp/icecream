import os
import json
import copy
from flask import Flask, request, jsonify
app = Flask(__name__)

class CMaze(object):
    def __init__(self):
        self.points = 50
        self.at_end = False
        self.start = [1, 2]
        self.end = [5, 3]
        self.current = copy.copy(self.start)
        self.maze = [[False, False, False, False, False, False],
                     [False, False, True,  True,  True,  False],
                     [False, False, False, False, True,  False],
                     [False, True,  True,  True,  True,  False],
                     [False, True,  False, False, True,  False],
                     [False, True,  True,  True,  True,  False],
                     [False, False, False, False, False, False]]
                     
    def get_current(self):
        return {'x': self.current[1], 'y': self.current[0]}
        
    def get(self, pos):
        return self.maze[pos[0]][pos[1]]
        
    def cleanup(self):
        self.points = 50
        self.at_end = False
        self.current = copy.copy(self.start)
        
    def move(self, x, y):
        pos = copy.copy(self.current)
        pos[0] += y
        pos[1] += x
        if self.get(pos):
            self.points += 3
            self.current = pos
            if pos == self.end and not self.at_end:
                self.at_end = True
                self.points -= 50
                
            print(self.points)
            return True
        else:
            self.points += 7
            print(self.points)
            return False
        
    def move_up(self):
        return self.move(0, -1)

    def move_down(self):
        return self.move(0, 1)
        
    def move_right(self):
        return self.move(1, 0)
        
    def move_left(self):
        return self.move(-1, 0)


maze = CMaze()

@app.route("/")
def hello():
    return "Hello World!"

@app.route('/StartCompetition', methods=['GET', 'POST'])
def start():
    if request.method == 'POST':
        params = request.get_json(force=True)
        print(params)
        maze.cleanup()
        return jsonify({'startPoint': {'x': maze.start[1], 'y': maze.start[0]}, 'endPoint': {'x': maze.end[1], 'y': maze.end[0]}})
    else:
        return jsonify({'startPoint': {'x': maze.start[1], 'y': maze.start[0]}, 'endPoint': {'x': maze.end[1], 'y': maze.end[0]}})
        
@app.route('/MoveUp', methods=['GET', 'POST'])
def move_up():
    if maze.move_up():
        return jsonify({'position': maze.get_current(), 'details': ' ', 'outcome': 'success'})
    else:
        return jsonify({'position': maze.get_current(), 'details': '#', 'outcome': 'failure'})
        
@app.route('/MoveDown', methods=['GET', 'POST'])
def move_down():
    if maze.move_down():
        return jsonify({'position': maze.get_current(), 'details': ' ', 'outcome': 'success'})
    else:
        return jsonify({'position': maze.get_current(), 'details': '#', 'outcome': 'failure'})


@app.route('/MoveRight', methods=['GET', 'POST'])
def move_right():
    if maze.move_right():
        return jsonify({'position': maze.get_current(), 'details': ' ', 'outcome': 'success'})
    else:
        return jsonify({'position': maze.get_current(), 'details': '#', 'outcome': 'failure'})        

@app.route('/MoveLeft', methods=['GET', 'POST'])
def move_left():
    if maze.move_left():
        return jsonify({'position': maze.get_current(), 'details': ' ', 'outcome': 'success'})
    else:
        return jsonify({'position': maze.get_current(), 'details': '#', 'outcome': 'failure'})

if __name__ == '__main__':
    app.run(os.getenv('IP', '0.0.0.0'), os.getenv('PORT', '8080'))