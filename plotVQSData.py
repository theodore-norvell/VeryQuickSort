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

fig, axis = plt.subplots(nrows=1, ncols=1)
axis.set_title( 'Distribution of times for VeryQuickSort on 127,400 random arrays')

viridisBig = cm.get_cmap('viridis', 512)
newColorMap = colors.ListedColormap(viridisBig(np.linspace(0.25, 1.0, 256)))
h = plot1( axis, random, newColorMap, 2000, 100 )
plt.colorbar(h[3], ax=axis)

fig, axis = plt.subplots(nrows=1, ncols=1)
axis.set_title( "'Worst', average, and best case times for VeryQuickSort")

h = plot1( axis, random, newColorMap, 2000, 100 )
plot2( axis, reversed, '^', 'black', "'Worst' case (descending arrays)" )
plot2( axis, average, 'o', 'black', 'Average case (based on 127,400 random arrays)' )
plot2( axis, sorted, 'v', 'black', 'Best case (ascending array)' )
axis.legend()

fig, axis = plt.subplots(nrows=1, ncols=1)
axis.set_title( "'Worst case times for VeryQuickSort" )

plot2( axis, reversed, '^', 'black', "'Worst' case (descending arrays)" )

x = np.linspace(1, 51, 400)
y1 = 0.666667 * x * x 
line, = axis.plot( x, y1, 'r' ) 
line.set_label( '2/3\N{MULTIPLICATION SIGN}N\N{SUPERSCRIPT TWO}' ) ;
y2 = 1 * x * x 
line, = axis.plot( x, y2, 'b' )
line.set_label( 'N\N{SUPERSCRIPT TWO}' ) ;
axis.legend() ;

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

plt.show()
