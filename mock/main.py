import os
import sys
import json
import copy
from flask import Flask, request, jsonify
app = Flask(__name__)

class CMaze(object):
    def __init__(self, filename):
        self.points = 50
        self.at_end = False
        self.maze = []
        f = open(filename, 'r')

        for line in enumerate(f):
            l = []
            for c in enumerate(line[1]):
                l.append(c[1] != '#')
                if c[1] == 'A':
                    self.start = [line[0], c[0]]
                elif c[1] == 'B':
                    self.end = [line[0], c[0]]
            self.maze.append(l)
                
        f.close()
        self.current = copy.copy(self.start)
        
        for i in self.maze:
            print(''.join([' ' if x else '#' for x in i]))
        print(self.start)
        print(self.end)
                     
    def get_current(self):
        return {'x': self.current[1], 'y': self.current[0]}
        
    def get(self, pos):
        return self.maze[pos[0]][pos[1]]
        
    def get_symbol(self, pos):
        return ' ' if self.get(pos) else '#'
        
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
                print('First time at end!!!')
                self.at_end = True
                self.points -= 50
            
            print(self.current)    
            print(self.points)
            return True
        else:
            self.points += 7
            print(self.current)
            print(self.points)
            return False
            
    def scan(self, direction=None):
        pos = copy.copy(self.current)
        res = {}
        if not direction:
            res['left'] = self.get_symbol((pos[0], pos[1] - 1))
            res['right'] = self.get_symbol((pos[0], pos[1] + 1))
            res['up'] = self.get_symbol((pos[0] - 1, pos[1]))
            res['down'] = self.get_symbol((pos[0] + 1, pos[1]))
        
        self.points += 4
        print(res)
        print(self.points)
        return res
            
        
    def move_up(self):
        return self.move(0, -1)

    def move_down(self):
        return self.move(0, 1)
        
    def move_right(self):
        return self.move(1, 0)
        
    def move_left(self):
        return self.move(-1, 0)


maze = CMaze(sys.argv[1])

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
        
@app.route('/Scan', methods=['GET', 'POST'])
def scan():
    return jsonify(maze.scan())
        
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