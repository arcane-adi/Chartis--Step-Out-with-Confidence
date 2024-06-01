import nltk
from nltk.tokenize import word_tokenize
from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer
from nltk.sentiment.vader import SentimentIntensityAnalyzer
import string
import pandas as pd
from nltk.tokenize import sent_tokenize

# Categorize the reviews
categories = {
    "theft": ["theft", "stolen", "robbery"],
    "animal trespassing": ["animal", "trespass", "garden"],
    "natural disaster": ["flood", "earthquake", "disaster"],
    "accidents": ["accident", "collision", "crash"],
    "criminal incidents": ["crime", "criminal", "incident"],
    "crowded": ["crowd", "crowded", "crowding"]
}

df = pd.read_excel('hadpsar_depo.xlsx')
all_reviews = ' '.join(df['Reviews'])

# Tokenize the text into sentences
reviews = sent_tokenize(all_reviews)

# Function to preprocess the text
def preprocess_text(text):
    # Tokenize the text
    tokens = word_tokenize(text.lower())

    # Remove stopwords
    stop_words = set(stopwords.words('english'))
    tokens = [word for word in tokens if word not in stop_words]

    # Remove punctuation
    tokens = [word for word in tokens if word not in string.punctuation]

    # Lemmatization
    lemmatizer = WordNetLemmatizer()
    tokens = [lemmatizer.lemmatize(word) for word in tokens]

    return tokens

# Sentiment analyzer
sid = SentimentIntensityAnalyzer()

# Function to categorize reviews and analyze sentiment
def categorize_reviews_with_sentiment(reviews, categories):
    categorized_reviews = {category: {"reviews": [], "sentiment": []} for category in categories}

    for review in reviews:
        tokens = preprocess_text(review)
        sentiment = sid.polarity_scores(review)

        for token in tokens:
            for category, keywords in categories.items():
                if token in keywords:
                    categorized_reviews[category]["reviews"].append(review)
                    categorized_reviews[category]["sentiment"].append(sentiment)
                    break

    return categorized_reviews

categorized_reviews = categorize_reviews_with_sentiment(reviews, categories)

def map_sentiment_to_danger_zone(sentiment_score):
    # Ensure sentiment score is within the range of -1 to 1
    sentiment_score = max(min(sentiment_score, 1), -1)

    # Map the sentiment score to a danger zone scale (1 to 10)
    danger_zone_score = (sentiment_score + 1) * 4.5 + 1  # Scale the sentiment score to 1-10 range
    return round(danger_zone_score, 1)  # Round to one decimal place

def map_sentiment_to_scale(sentiment_score):
    #Map the sentiment score from -1 to 1 to a 1 to 10 scale
    scaled_score = (sentiment_score + 1) * 5
    return round(scaled_score, 1)  # Round to one decimal place

# Sample sentiment score from VADER sentiment analyzer
#sentiment_score = 0.4404

# Print the categorized reviews with sentiment analysis
for category, data in categorized_reviews.items():
    if data["reviews"]:  # Check if there are reviews in this category
        print(f"{category.capitalize()}\n")
        for i in range(len(data["reviews"])):
            review = data["reviews"][i]
            sentiment = data["sentiment"][i]
            #print(f"Review: {review}")
        # Sample sentiment dictionary
        sentiment_dict = sentiment

        # Abstract the compound score
        sentiment_score = sentiment_dict['compound']
        # Map the sentiment score to a 1 to 10 scale
        scaled_sentiment = map_sentiment_to_danger_zone(sentiment_score)

        print(f"{scaled_sentiment}")
        #print("\n")