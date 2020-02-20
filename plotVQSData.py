# Copyright (c) 2020 Theodore Norvell
# 
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
# 
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
# 
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.

import matplotlib.cm as cm
import matplotlib.pyplot as plt
import matplotlib.colors as colors
import numpy as np

def plot1( axis, data, cmap, yCap, yBins ) :
    x = data[ :, 0]
    y = data[ :, 4]
    h = axis.hist2d(x, y, range=[[0,51],[0,yCap]], cmin=1, cmap=cmap, bins=[51, yBins] )
    return h

def plot2( axis, data, marker, color, label ) :
    x = data[ :, 0]
    y = data[ :, 4]
    h = axis.plot(x, y, marker=marker, color=color, linestyle='' )
    line, = h
    line.set_label( label ) 
    return h

random = np.genfromtxt( 'random.csv', delimiter=',' ) ;
sorted = np.genfromtxt( 'sorted.csv', delimiter=',' ) ;
reversed = np.genfromtxt( 'reversed.csv', delimiter=',' ) ;
average = np.genfromtxt( 'average.csv', delimiter=',' ) ;
almost =  np.genfromtxt( 'almost.csv', delimiter=',' ) ;
almostAv =  np.genfromtxt( 'almostAverage.csv', delimiter=',' ) ;


# New Figure: Distribution of times.
fig, axis = plt.subplots(nrows=1, ncols=1)
axis.set_title( 'Distribution of times for VeryQuickSort on 127,400 random arrays')

viridisBig = cm.get_cmap('viridis', 512)
newColorMap = colors.ListedColormap(viridisBig(np.linspace(0.25, 1.0, 256)))
h = plot1( axis, random, newColorMap, 2000, 100 )
plt.colorbar(h[3], ax=axis)

# New Figure: Everything
fig, axis = plt.subplots(nrows=1, ncols=1)
axis.set_title( "'Worst', average, and best case times for VeryQuickSort")

h = plot1( axis, random, newColorMap, 2000, 100 )
plot2( axis, reversed, '^', 'black', "'Worst' case (descending arrays)" )
plot2( axis, average, 'o', 'black', 'Average case (based on 127,400 random arrays)' )
plot2( axis, sorted, 'v', 'black', 'Best case (ascending arrays)' )
axis.legend()

# New Figure: Worst case
fig, axis = plt.subplots(nrows=1, ncols=1)
axis.set_title( "'Worst' case times for VeryQuickSort" )

plot2( axis, reversed, '^', 'black', "'Worst' case (descending arrays)" )

x = np.linspace(1, 51, 400)
y1 = 0.666667 * x * x 
line, = axis.plot( x, y1, 'r' ) 
line.set_label( '2/3\N{MULTIPLICATION SIGN}N\N{SUPERSCRIPT TWO}' ) ;


# Trim the blue line so that it doesn't go above the highest y value
y = reversed[:,4]
maxY = y.max()   # Find the maximum y value
y2 = x*x
z = np.vstack( (x, y2) ) # Make a 2 by 400 array
z1 = z[ :, y2 <= maxY ]  # Select the columns where y2 <= maxY
x = z1[0, :]  
y2 = z1[1, :]

line, = axis.plot( x, y2, 'b' )
line.set_label( 'N\N{SUPERSCRIPT TWO}' ) ;
axis.legend() ;

# New Figure: Average case
fig, axis = plt.subplots(nrows=1, ncols=1)
axis.set_title( 'Average case times for VeryQuickSort' )

plot2( axis, average, 'o', 'black', 'Average case (based on 127,400 random arrays)' )

x = np.linspace(1, 51, 400)
y1 = 2 * np.log2(x)*x ;
line, = axis.plot( x, y1, 'r' ) 
line.set_label( '2\N{MULTIPLICATION SIGN}N\N{MULTIPLICATION SIGN}log\N{SUBSCRIPT TWO}N' ) ;
y2 = 3 * np.log2(x)*x
line, = axis.plot( x, y2, 'b' )
line.set_label( '3\N{MULTIPLICATION SIGN}N\N{MULTIPLICATION SIGN}log\N{SUBSCRIPT TWO}N' ) ;
axis.legend() ;

# New Figure, Best case
fig, axis = plt.subplots(nrows=1, ncols=1)
axis.set_title( 'Best case times for VeryQuickSort' ) 

plot2( axis, sorted, 'v', 'black', 'Best case (ascending arrays)' )

x = np.linspace(1, 51, 400)
y1 = 1.75 * x
line, = axis.plot( x, y1, 'r' ) 
line.set_label( '1.75\N{MULTIPLICATION SIGN}N' ) ;
y2 = 2.25 *x
line, = axis.plot( x, y2, 'b' )
line.set_label( '2.25\N{MULTIPLICATION SIGN}N' ) ;
axis.legend() ;

# New Figure: Comparing average on random arrays to average on almost sorted arrays
fig, axis = plt.subplots(nrows=1, ncols=1)
axis.set_title( "VeryQuickSort on random and almost sorted data")

h = plot1( axis, random, newColorMap, 800, 100 )
plot2( axis, average, 'o', 'black', 'Average case (based on 127,400 random arrays)' )

springCM = cm.get_cmap('spring', 256)
h = plot1( axis, almost, springCM, 800, 100 )
plot2( axis, almostAv, '*', 'black', 'Average on almost sorted arrays.' )

plot2( axis, sorted, 'v', 'black', 'Best case (ascending arrays)' )

axis.legend()

plt.show()
