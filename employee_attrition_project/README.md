# Employee Attrition Prediction Using Machine Learning

## Project Overview
This project implements three classification algorithms (Logistic Regression, Decision Trees, Random Forest) to predict employee attrition using the IBM HR Analytics dataset. The study focuses on addressing class imbalance through SMOTE (Synthetic Minority Over-sampling Technique) to improve recall for practical HR applications.

## Dataset
- **Source**: [IBM HR Analytics Employee Attrition & Performance](https://www.kaggle.com/datasets/pavansubhasht/ibm-hr-analytics-attrition-dataset)
- **Size**: 1,470 employees, 35 features
- **Class Distribution**: 16.1% attrition rate (237 cases)

## Key Results
- **Baseline Random Forest**: 88.8% accuracy, 10.6% recall
- **SMOTE-enhanced Random Forest**: 82.3% accuracy, 25.5% recall
- **Business Value**: €345,000 estimated net savings
- **Top Predictors**: Stock Option Level (6.9%), Marital Status (5.0%), Job Satisfaction (4.9%)

## Installation & Requirements

```bash
pip install -r requirements.txt
```

## Usage

```bash
jupyter notebook employee_attrition_practical.ipynb
```

## Tools & Libraries
- Python 3.x
- scikit-learn (Pedregosa et al., 2011)
- imbalanced-learn (Lemaître et al., 2017)
- pandas
- matplotlib
- seaborn

## Project Structure
```
employee-attrition-prediction/
├── README.md
├── employee_attrition_practical.ipynb
├── requirements.txt
├── data/
│   └── WA_Fn-UseC_-HR-Employee-Attrition.csv
└── images/
    ├── output_7_0.png
    ├── output_17_0.png
    ├── output_31_0.png
    └── output_35_1.png
```

## Results Summary
The SMOTE-enhanced Random Forest model achieved a 140% improvement in recall (10.6% → 25.5%), correctly identifying 12 of 47 employees who left. While the F1-score of 0.316 falls short of academic benchmarks (Chung et al., 2023: 0.974), the model demonstrates clear practical value for HR interventions with favorable cost-benefit analysis.

## Key Findings
- **Class imbalance** emerged as the primary obstacle to achieving practically useful results
- **SMOTE application** substantially improved model utility for HR decision-making
- **Feature importance analysis** revealed Stock Option Level as the strongest predictor, followed by Marital Status and Job Satisfaction
- **Business value** demonstrated through €345,000 estimated net savings from preventing seven additional employee departures

## Methodology
1. **Data Preprocessing**: One-hot encoding for categorical variables, stratified train-test split (80/20)
2. **Baseline Models**: Logistic Regression, Decision Trees, Random Forest on imbalanced data
3. **SMOTE Application**: Balanced training set to 987 examples per class
4. **Evaluation**: Five-fold cross-validation with focus on recall and F1-score
5. **Feature Analysis**: Random Forest feature importance for interpretability

## Author
Georgios-Efstratios Maistrelis  
BSc Computing, University of Greater Manchester
Module: CLD6000 Contemporary Problem Analysis

## References
This implementation is based on:
- **SMOTE Technique**: Chawla, N. V., et al. (2002). SMOTE: Synthetic Minority Over-sampling Technique
- **Benchmark Comparison**: Chung, Y.-C., et al. (2023). Predictive model of employee attrition based on stacking ensemble learning (F1-score: 0.974)
- **Tools**: scikit-learn (Pedregosa et al., 2011), imbalanced-learn (Lemaître et al., 2017)

For the complete bibliography and theoretical background, refer to the accompanying project report.

## License
Academic project - University of Greater Manchester
---

**Note**: This project was completed as part of the CLD6000 Contemporary Problem Analysis module. The implementation prioritizes recall over balanced F1-score to align with business objectives where missing at-risk employees is more costly than investigating false positives.
