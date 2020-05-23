# Non-Personalized-Recommender-systems
### Non-personalised Recommender Framework
The following provides a description of the dataset and the non-personalised recommender framework that are used in this part of the assignment. To help get you started, the framework contains an implementation of a non-personalised algorithm,in which the similarity between two movies is calculated over the set of genresassociated with each movie. This algorithm is ready to run. The framework is designed tobe extensibleand it readily facilitates the integration of the additional similarity metrics which you need to implement in this part of the assignment. 
### Dataset
The assignment dataset consists of: 
•95,379 ratings from 1,135users on 1,660movies. 
•Ratings are based on a 0.5–5.0 point scale, in increments of 0.5, where 5.0 is the maximum rating and 0.5 is the minimum rating.
The data was obtained from the MovieLens system(https://movielens.org/). This dataset includes the following files.
#### Training Set(train.txt)
The training set consists of a randomly selected 80% of the user-item ratings from the dataset.The file format is as follows:each line consists of a <user_id,item_id,rating>rating tuple. For example, the first line in the file is:16387,3360,4.5, which indicates that user 16387assigned a rating of 4.5to item 3360.
#### TestSet (test.txt)
This file consists of 10% of the ratings from the dataset(these ratingsare different from those containedin the training set). Note that this file is not used in this assignment on non-personalised recommender algorithms.
#### Item Descriptions (movies-sample.txt)
This file containsinformation about the movies. Each line of this file represents a movie which is formatted as follows:<item_id,title,genre_1|genre_2|...|genre_N>.For example, the first line in this file is: 1,ToyStory (1995),Adventure|Animation|Children|Comedy|Fantasywhich showstheset of genresassociated with the movie with ID1and title Top Story (1995).The movie IDs correspond to those used in the training andtest sets.
#### Genome Tags (genome-tags.csv) and Genome Scores (genome-scores-sample.txt)
The tag genome contains tag relevance scores for movies.Each movie has a value for every one of the 1,128 tagsin the genome.The tag genome encodes how strongly movies exhibit particular properties represented by tags (atmospheric, thought-provoking, realistic, etc.). The tag genome was computed using a machine learning algorithm on user-contributed content including tags, ratings, and textual reviews.The genome is split into two files. The file “genome-scores-sample.txt”contains movie-tag relevance data in the following format: <movieId,tagId,relevance>.The movie IDs correspond to those used in the training and test sets.The second file, “genome-tags.csv”, provides the tag descriptions for the tag IDs in the genome file, in the following format: <tag_id,tag>.
### Non-personalisedRecommender Algorithm
This algorithm makes recommendations for a given target item. The key step in the algorithm is to calculate the similarities between items.Non-personalised recommender algorithms are characterised by the similarity metric used.Calculating item-item similarity: the framework contains an implementation of the Genre similarity metric (class GenreMetric in package alg.np.similarity.metric). Note that this class implements the interface SimilarityMetric(also in package alg.np.similarity.metric). All similarity metrics should implementthe SimilarityMetricinterface. This way, it is very easy to change the code to switch to a different similarity metric. 




